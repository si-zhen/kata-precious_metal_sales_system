package com.coding.sales;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.coding.sales.business.bean.Member;
import com.coding.sales.business.bean.Metal;
import com.coding.sales.business.calculate.CalculateDiscountUtil;
import com.coding.sales.business.calculate.CalculateDiscountUtil.CalculateDiscountResult;
import com.coding.sales.business.db.MemberInfo;
import com.coding.sales.business.db.MetalInfo;
import com.coding.sales.business.utils.MathUtil;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;

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
        Member member = MemberInfo.findMemberById(memberId);
        String oldMemberType = member.getLevel().getLevelDesc();
        
        List<String> discountTickets = command.getDiscounts();
        
        
        List<OrderItemRepresentation> orderItems = new ArrayList<>();
        
        // 购买商品
		List<OrderItemCommand> items = command.getItems();
		BigDecimal totalPrice = new BigDecimal(0);
		for (OrderItemCommand orderItemCommand : items) {
			String productId = orderItemCommand.getProduct();
			Metal metal = MetalInfo.findMetalById(productId);
			BigDecimal total = MathUtil.multiply(metal.getPrice(), orderItemCommand.getAmount(), 2,
					RoundingMode.HALF_UP);
			OrderItemRepresentation orderItem = new OrderItemRepresentation(productId, metal.getName(),
					metal.getPrice(), orderItemCommand.getAmount(), total);
			orderItems.add(orderItem);
			
			CalculateDiscountResult discountResult = CalculateDiscountUtil.calMiniestPay(metal, orderItemCommand.getAmount(), discountTickets);
			
			
			
			totalPrice.add(total);
		}
        
        
        
        result = new OrderRepresentation(command.getOrderId(), command.getCreateTime(), 
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
}
