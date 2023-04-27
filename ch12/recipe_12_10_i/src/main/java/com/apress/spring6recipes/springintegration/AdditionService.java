package com.apress.spring6recipes.springintegration;


/**
 * simple example designed to demonstrate adding over queues! Horribly inefficient, but it demonstrates a point.
 */
public class AdditionService {

    public Number add(Operands ops) {
        return (ops.a().floatValue() + ops.b().floatValue());
    }

}
