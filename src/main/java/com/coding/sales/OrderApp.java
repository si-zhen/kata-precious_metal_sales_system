package com.coding.sales;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coding.sales.business.bean.CalculateResult;
import com.coding.sales.business.bean.Member;
import com.coding.sales.business.bean.Metal;
import com.coding.sales.business.calculate.CalculateDiscountUtil;
import com.coding.sales.business.calculate.CalculateReductionUtil;
import com.coding.sales.business.constants.MemberLevel;
import com.coding.sales.business.db.MemberInfo;
import com.coding.sales.business.db.MetalInfo;
import com.coding.sales.business.utils.MathUtil;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);
        
        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        OrderRepresentation result = null;

        //TODO: 请完成需求指定的功能
        
        String memberId = command.getMemberId();
        List<PaymentCommand> payment = command.getPayments();
        List<String> discountCards = command.getDiscounts();
        Member member = MemberInfo.findMemberById(memberId);
        int oldPoint = member.getPoint();
        String oldMemberType = member.getLevel().getLevelDesc();
        
        List<String> discountTickets = discountCards;
        
        
        List<OrderItemRepresentation> orderItems = new ArrayList<>();
        List<DiscountItemRepresentation> discounts = new ArrayList<>();
        // 购买商品
		List<OrderItemCommand> items = command.getItems();
		
		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal receivables = new BigDecimal(0);
		BigDecimal totalDiscountPrice = new BigDecimal(0);
		
		// 打折后的计算结果
		CalculateResult discountResult = null;
		// 满减后的计算结果
		CalculateResult reductionResult = null;
		// 最终采用的计算结果
		CalculateResult finalResult = null;
		for (OrderItemCommand orderItemCommand : items) {
			String productId = orderItemCommand.getProduct();
			Metal metal = MetalInfo.findMetalById(productId);
			BigDecimal total = MathUtil.multiply(metal.getPrice(), orderItemCommand.getAmount(), 2,
					RoundingMode.HALF_UP);
			OrderItemRepresentation orderItem = new OrderItemRepresentation(productId, metal.getName(),
					metal.getPrice(), orderItemCommand.getAmount(), total);
			orderItems.add(orderItem);
			discountResult = CalculateDiscountUtil.calMiniestPay(metal, orderItemCommand.getAmount(), discountTickets);
			reductionResult = CalculateReductionUtil.calMiniestPay(metal, orderItemCommand.getAmount());
			if (discountResult.getDiscountMoney().compareTo(reductionResult.getDiscountMoney()) > 0) {
				finalResult = discountResult;
			} else {
				finalResult = reductionResult;
			}

			DiscountItemRepresentation discountItemRepresentation = new DiscountItemRepresentation(memberId,
					metal.getName(), finalResult.getDiscountMoney());
			discounts.add(discountItemRepresentation);

			totalDiscountPrice.add(finalResult.getDiscountMoney());
			totalPrice.add(total);
		}
        //支付整数金额
		BigDecimal integerTotal = receivables.setScale(0, RoundingMode.DOWN);
		//新增积分
		int memberPointsIncreased = member.getPopintStrategy().calculatePoints(integerTotal);
        //新总积分
		int memberPoints = oldPoint + memberPointsIncreased;
		//更新用户等级
		level(member, memberPoints);
		//获取并设置支付方式
		List<PaymentRepresentation> payments = new ArrayList<>();
		for(PaymentCommand pay : payment){
			PaymentRepresentation pays = new PaymentRepresentation(pay.getType(), pay.getAmount());
			payments.add(pays);
		}
        result = new OrderRepresentation(command.getOrderId(), new Date(), 
        		member.getMemberId(), member.getName(), 
        		oldMemberType, 
        		member.getLevel().getLevelDesc(), 
        		memberPointsIncreased, 
        		memberPoints, 
        		orderItems, 
        		totalPrice, 
        		discounts, 
        		totalDiscountPrice, 
        		receivables, 
        		payments, 
        		discountCards);
        

        return result;
    }

	private void level(Member member, int memberPoints) {
		if(memberPoints < 10000){
			member.setLevel(MemberLevel.NORMAL);
		}else if(memberPoints >= 10000 && memberPoints < 50000){
			member.setLevel(MemberLevel.GOLD);
		}else if(memberPoints >= 5000 && memberPoints < 100000){
			member.setLevel(MemberLevel.PLATINUM);
		}else if(memberPoints >= 100000){
			member.setLevel(MemberLevel.DIAMONDS);
		}
	}
}
