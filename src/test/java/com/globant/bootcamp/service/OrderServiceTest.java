package com.globant.bootcamp.service;

import com.globant.bootcamp.exception.BadRequestException;
import com.globant.bootcamp.exception.EmptyCartException;
import com.globant.bootcamp.model.*;
import com.globant.bootcamp.persistence.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @SpringBootTest public class OrderServiceTest {

	private OrderService orderService;

	private OrderRepository orderRepository;

	private User testUser;

	@Before public void contextLoads() {
		testUser = new User("email@test.com","gman","1234");
		PaymentMethodRepository pmRepo = mock(PaymentMethodRepository.class);
		DeliverMethodRepository dmRepo = mock(DeliverMethodRepository.class);
		StatusRepository sRepo = mock(StatusRepository.class);
		when(pmRepo.findById((long) 1)).thenReturn(Optional.of(new PaymentMethod()));
		when(dmRepo.findById((long) 1)).thenReturn(Optional.of(new DeliverMethod()));
		when(sRepo.findById((long) 1)).thenReturn(Optional.of(new Status()));
		orderRepository = mock(OrderRepository.class);
		this.orderService = new OrderService(orderRepository,pmRepo,dmRepo,sRepo);
	}

	@Test
	public void whenCreateAnOrderThenReturnAnOrder(){
		Order newOrder = this.orderService.createOrder(testUser, mockCart(),(long) 1,(long) 1);
		assertEquals(1,newOrder.getOrderLines().size());
		assertEquals(100.0,newOrder.getTotal());
	}

	@Test(expected = BadRequestException.class)
	public void whenSomeParameterIsIncorrectThenThrowAnException() throws BadRequestException {
		this.orderService.createOrder(testUser,new Cart(testUser),(long) 2,(long) 3);
	}

	@Test(expected = EmptyCartException.class)
	public void whenTheCartIsEmptyThenThrowAnException() throws EmptyCartException {
		this.orderService.createOrder(testUser,new Cart(testUser),(long) 1,(long) 1);
	}

	@Test
	public void whenGetAllNotFinishedOrdersListReturnAListOfOrders(){
		List<Order> orderList = mockOrderList();
		when(orderRepository.findAllByUser(testUser)).thenReturn(orderList);
		assertEquals(2,this.orderService.getNotFinishedUserOrders(testUser).size());
	}

	private List<Order> mockOrderList(){
		List<Order> orderList = new ArrayList<>();
		Order toPrepareOrder = mock(Order.class);
		when(toPrepareOrder.getStatus()).thenReturn(createMockStatus(1));
		Order toDeliverOrder = mock(Order.class);
		when(toDeliverOrder.getStatus()).thenReturn(createMockStatus(2));
		Order finishedOrder = mock(Order.class);
		when(finishedOrder.getStatus()).thenReturn(createMockStatus(3));
		orderList.add(toPrepareOrder);
		orderList.add(toDeliverOrder);
		orderList.add(finishedOrder);
		return orderList;
	}

	private Status createMockStatus(int id){
		Status s = new Status();
		s.setId((long)id);
		return  s;
	}

	public Cart mockCart() {
		Cart c = new Cart(testUser);
		Product p = mock(Product.class);
		when(p.getId()).thenReturn((long)1);
		when(p.getPrice()).thenReturn(10.0);
		c.addProduct(p,10);
		return c;
	}

}
