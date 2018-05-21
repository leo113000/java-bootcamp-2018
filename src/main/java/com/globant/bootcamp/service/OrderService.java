package com.globant.bootcamp.service;

import com.globant.bootcamp.model.*;
import com.globant.bootcamp.persistence.DeliverMethodRepository;
import com.globant.bootcamp.persistence.OrderRepository;
import com.globant.bootcamp.persistence.PaymentMethodRepository;
import com.globant.bootcamp.persistence.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	public OrderService(OrderRepository orderRepository, PaymentMethodRepository paymentMethodRepository,
			DeliverMethodRepository deliverMethodRepository, StatusRepository statusRepository) {
		this.orderRepository = orderRepository;
		this.paymentMethodRepository = paymentMethodRepository;
		this.deliverMethodRepository = deliverMethodRepository;
		this.statusRepository = statusRepository;
	}

	public List<Order> getAll(){
		return this.orderRepository.findAll();
	}

	public Order createOrder(Cart cart, Long paymentMethodId, Long deliverMethodId, Long statusId){
		Order newOrder = null;
		Optional<PaymentMethod> pmOp = this.paymentMethodRepository.findById(paymentMethodId);
		Optional<DeliveryMethod> dmOp = this.deliverMethodRepository.findById(deliverMethodId);
		Optional<Status> sOp = this.statusRepository.findById(statusId);

		if(pmOp.isPresent() && dmOp.isPresent() && sOp.isPresent()){
			newOrder = this.orderRepository.save(new Order(cart, new Date(), dmOp.get(), pmOp.get(), sOp.get()));
		}
		return newOrder;
	}
}
