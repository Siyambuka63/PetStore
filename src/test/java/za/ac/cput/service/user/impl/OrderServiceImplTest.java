package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
import za.ac.cput.factory.user.ContactFactory;
import za.ac.cput.factory.user.UserFactory;
import za.ac.cput.service.impl.OrderServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl service;

    @Test
    void a_create() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);


        Order order = OrderFactory.createOrder(1, user, orderDate, deliveryDate, 8000, status);

        System.out.println("Order to be created: " + order);
        Order created = service.create(order);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-08-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

        Order order = OrderFactory.createOrder(1, user, orderDate, deliveryDate, 8000, status);
        System.out.println("Order read +" + order);
        assertNotNull(order);

        Order read = service.read(order.getId());
        assertNotNull(read);
        System.out.println("read: " + read);
    }

    @Test
    void c_update() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

        Order order = OrderFactory.createOrder(1, user, orderDate, deliveryDate, 8000, status);

        assertNotNull(order);
        Order newOrder = new Order.Builder().copy(order).setPrice(30000).build();
        Order updatedOrder = service.update(newOrder);
        assertNotNull(updatedOrder);
        System.out.println(updatedOrder);
    }

    @Test
    @Disabled
    void d_delete() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

        Order order = OrderFactory.createOrder(1, user, orderDate, deliveryDate, 8000, status);

        service.delete(order.getId());

        Order deleted = service.read(order.getId());
        assertNull(deleted);
        System.out.println(deleted);
    }

    @Test
    void e_getAll() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

        Order order = OrderFactory.createOrder(1, user, orderDate, deliveryDate, 8000, status);

        System.out.println(service.getAll());
    }

    @Test
    void f_findByOrderDate() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

        Order order = OrderFactory.createOrder(1, user, orderDate, deliveryDate, 8000, status);

        assertNotNull(service.findByOrderDate(orderDate));
        System.out.println(service.findByOrderDate(orderDate));
    }

    @Test
    void g_findByDeliveryDate() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

        Order order = OrderFactory.createOrder(1, user, orderDate, deliveryDate, 8000, status);

        assertNotNull(service.findByDeliveryDate(deliveryDate));
        System.out.println(service.findByDeliveryDate(deliveryDate));
    }

    @Test
    void h_findByTotalPrice() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

        Order order = OrderFactory.createOrder(1, user, orderDate, deliveryDate, 8000, status);

        assertNotNull(service.findByPrice(order.getPrice()));
        System.out.println(service.findByPrice(order.getPrice()));
    }

    @Test
    void i_findByStatus() {
        Status status = Status.Busy;

        assertNotNull(service.findByStatus(status));
        System.out.println(service.findByStatus(status));
    }
}