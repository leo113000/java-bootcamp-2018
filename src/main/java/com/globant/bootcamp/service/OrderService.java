package com.globant.bootcamp.service;

import com.globant.bootcamp.exception.BadRequestException;
import com.globant.bootcamp.exception.EmptyCartException;
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
import java.util.stream.Collectors;

@Service
public class OrderService {

	@Autowired private OrderRepository orderRepository;
	@Autowired private PaymentMethodRepository paymentMethodRepository;
	@Autowired private DeliverMethodRepository deliverMethodRepository;
	@Autowired private StatusRepository statusRepository;

	private final Long INITIAL_ORDER_STATUS_ID = (long)1;
	private final Long LAST_ORDER_STATUS_ID = (long)3;

	public OrderService(OrderRepository orderRepository, PaymentMethodRepository paymentMethodRepository,
			DeliverMethodRepository deliverMethodRepository, StatusRepository statusRepository) {
		this.orderRepository = orderRepository;
		this.paymentMethodRepository = paymentMethodRepository;
		this.deliverMethodRepository = deliverMethodRepository;
		this.statusRepository = statusRepository;
	}

	public Order createOrder(User user,Cart cart, Long paymentMethodId, Long deliverMethodId) throws BadRequestException, EmptyCartException{
		Order newOrder;

		if(cart.getProductList().isEmpty()){
			throw new EmptyCartException();
		}

		Optional<PaymentMethod> pmOp = this.paymentMethodRepository.findById(paymentMethodId);
		Optional<DeliverMethod> dmOp = this.deliverMethodRepository.findById(deliverMethodId);
		Optional<Status> sOp = this.statusRepository.findById(INITIAL_ORDER_STATUS_ID);

		if(pmOp.isPresent() && dmOp.isPresent() && sOp.isPresent()){
			newOrder = new Order(user, new Date(), dmOp.get(), pmOp.get(), sOp.get());
			newOrder.addCartLines(cart);
			this.orderRepository.save(newOrder);
		}else{
			throw new BadRequestException();
		}
		return newOrder;
	}

	public List<Order> getNotFinishedUserOrders(User user) {
		System.out.println(LAST_ORDER_STATUS_ID);
		return this.orderRepository.findAllByUser(user).stream()
				.filter(x -> !x.getStatus().getId().equals(LAST_ORDER_STATUS_ID)).collect(Collectors.toList());
	}
}
