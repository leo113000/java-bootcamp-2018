package com.globant.bootcamp.service;

import com.globant.bootcamp.model.*;
import com.globant.bootcamp.persistence.DeliverMethodRepository;
import com.globant.bootcamp.persistence.OrderRepository;
import com.globant.bootcamp.persistence.PaymentMethodRepository;
import com.globant.bootcamp.persistence.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

	@Autowired private OrderRepository orderRepository;
	@Autowired private PaymentMethodRepository paymentMethodRepository;
	@Autowired private DeliverMethodRepository deliverMethodRepository;
	@Autowired private StatusRepository statusRepository;

	@Value("${app.initialOrderStatusId}")
	private Long INITIAL_ORDER_STATUS_ID;

	public OrderService(OrderRepository orderRepository, PaymentMethodRepository paymentMethodRepository,
			DeliverMethodRepository deliverMethodRepository, StatusRepository statusRepository) {
		this.orderRepository = orderRepository;
		this.paymentMethodRepository = paymentMethodRepository;
		this.deliverMethodRepository = deliverMethodRepository;
		this.statusRepository = statusRepository;
	}

	public Order createOrder(User user,Cart cart, Long paymentMethodId, Long deliverMethodId){
		Order newOrder = null;
		Optional<PaymentMethod> pmOp = this.paymentMethodRepository.findById(paymentMethodId);
		Optional<DeliveryMethod> dmOp = this.deliverMethodRepository.findById(deliverMethodId);
		Optional<Status> sOp = this.statusRepository.findById(INITIAL_ORDER_STATUS_ID);

		if(pmOp.isPresent() && dmOp.isPresent() && sOp.isPresent()){
			newOrder = this.orderRepository.save(new Order(user, new Date(), dmOp.get(), pmOp.get(), sOp.get()));
			newOrder.addCartLines(cart);
		}
		return newOrder;
	}

	public List<Order> getUserOrders(User user) {
		return this.orderRepository.findAllByUser(user);
	}
}
