package com.globant.bootcamp;

import org.springframework.stereotype.Repository;
import java.util.Deque;

@Repository
public interface ShoppingCartRepository extends Deque<ShoppingCart> {
}
