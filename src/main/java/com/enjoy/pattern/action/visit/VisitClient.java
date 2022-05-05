package com.enjoy.pattern.action.visit;

import java.util.ArrayList;
import java.util.List;

import com.enjoy.pattern.create.fatory.simple.StaticFactory;
import com.enjoy.pattern.entity.Fruit;
import com.enjoy.pattern.entity.fruit.Apple;

/**
 * 访问者模式
 * 利用双重分派机制，弥补java多态中的方法重载是静态化的不足
 * 商品库存统计
 */
public class VisitClient {
    private static Visit visit = new Visit();
    private static List<Fruit> list = new ArrayList<>();//库存

    static {
        list.add(StaticFactory.getFruitApple());
        list.add(StaticFactory.getFruitOrange());
        list.add(StaticFactory.getFruitBanana());
        list.add(StaticFactory.getFruitApple());
        list.add(StaticFactory.getFruitOrange());
    }

    //统计库存
    private static int price() {
        int total = 0;
        for (Fruit fruit : list) {
//            total += visit.sell(fruit);
            total += fruit.accept(visit);
        }

        System.out.println("总价值：" + total);
        return total;
    }

    public static void main(String[] args) {
        price();
    }

    private static void test1() {
        Apple apple = new Apple();
        Fruit fruit = apple;

        int price = visit.sell(fruit);//不能识别fruit对象的真实类型
        System.out.println("fruit价格：" + price);

        price = visit.sell(apple);
        System.out.println("apple价格：" + price);
    }

    private static void test2() {
        Apple apple = new Apple();
        Fruit fruit = apple;

        int price = fruit.accept(visit);
        System.out.println("价格：" + price);
    }
}
