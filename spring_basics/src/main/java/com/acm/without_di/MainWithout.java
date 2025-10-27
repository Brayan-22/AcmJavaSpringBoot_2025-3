package com.acm.without_di;

public class MainWithout {
    interface BaseMotor{
        void start();
    }
//    static class Motor{
//        public void start() {
//            System.out.println("Motor started");
//        }
//    }
    static class Motor implements BaseMotor{
        @Override
        public void start() {
            System.out.println("Motor started");
        }
    }
    static class Car {
        private Motor motor;

        public Car() {
            this.motor = new Motor(); // Dependencia fuerte == Alto acoplamiento
        }

        public void drive() {
            motor.start();
            System.out.println("Car is driving");
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
    }
}

