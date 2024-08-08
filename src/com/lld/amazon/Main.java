package com.lld.amazon;

import com.lld.amazon.constant.*;
import com.lld.amazon.exception.PricingException;
import com.lld.amazon.model.*;
import com.lld.amazon.service.*;
import com.lld.amazon.service.impl.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static void test1(PricingService pricingService, OrderService orderService, Customer customer,
                              List<KV> customerPreferences) throws PricingException {
        Catalogue catalogue = new Catalogue();
        catalogue.addProduct(getProduct1(pricingService));

        HomePage home = new HomePage();
        home.addCatalogue(catalogue);

        HomePageService homePageService = new HomePageServiceImpl();
        List<Catalogue> catalogues = homePageService.chooseCatalogue(home);
        if (catalogues.isEmpty()) {
            throw new RuntimeException("Invalid test! No catalogue found");
        }

        List<Product> customerChosenProducts = homePageService.chooseProducts(catalogues);
        Product product = customerChosenProducts.get(0);

        System.out.println(product.getTitle());
        for (ProductVariety variety: product.getVarieties()) {
            System.out.println("Total amount: " + variety.getPricing().getTotalAmount());
        }

        CartService cartService = new CartServiceImpl(pricingService, orderService, customer, customerPreferences);
        Cart cart = cartService.addToCart(customer, product, product.getVarieties().get(0), 2);
        System.out.println("Cart cost: " + cart.getCartCost());

        // Customer can opt-in for any delivery option while checking out
        // The default is standard delivery
        DeliveryOption deliveryOption = new DeliveryOption(DeliveryType.ONE_DAY_DELIVERY,
                new DeliveryETA(LocalDateTime.now().plusDays(1)));

        Order order = cartService.checkout(cart, new PaymentMethod(
                PaymentMethodType.CASH_ON_DELIVERY, Collections.emptyList()),
                customer.getProfile().getDefaultDeliveryAddress(), deliveryOption
        );

        System.out.println("Order total: " + order.getOrderTotal());

        List<OrderItemDelivery> orderItemDeliveries = order.getOrderItemDeliveries();
        for (OrderItemDelivery delivery: orderItemDeliveries) {
            System.out.println(delivery.getItem().getProduct().getTitle() + " " + delivery.getItem().getChosenVariety().getTitleSuffix()
                    + " has ETA by date: " + delivery.getDeliveryETA().getETAHumanReadable());
        }

        Payment payment = orderService.pay(order);
        System.out.println("Payment status: " + payment.getPaymentStatus());
    }

    private static Product getProduct1(PricingService pricingService) throws PricingException {
        String title1 = "Redmi 13C (Starshine Green, 4GB RAM, 128GB Storage) " +
                "| Powered by 4G MediaTek Helio G85 | 90Hz Display | " +
                "50MP AI Triple Camera";
        String desc1 = "Processor: High performance MediaTek G85 ; Enhance gaming " +
                "with 1GHz GPU | 8GB of RAM including 4GB virtual | 6.74\" HD+ 90Hz " +
                "display with Corning Gorilla Glass 3 Protection | 50MP AI Triple " +
                "camera |Fast Side fingerprint | 5000mAh Battery";
        Seller seller = new Seller("Cloudtail");

        PricingGroup baseGroup = pricingService.registerPricingGroup(PricingGroups.BASE, PricingGroupType.ITEM_LEVEL, true);
        PricingGroup taxGroup = pricingService.registerPricingGroup(PricingGroups.TAX, PricingGroupType.ITEM_LEVEL, false);
        PricingGroup discountGroup = pricingService.registerPricingGroup(PricingGroups.DISCOUNT, PricingGroupType.ITEM_LEVEL, false);

        // Base price
        pricingService.registerPricingLineItemWithAmount(baseGroup, PricingLineItems.BASE_AMOUNT,
                new Money(11000D, CurrencySymbol.INR), null);

        // Tax
        pricingService.registerPricingLineItemWithPercentage(taxGroup, PricingLineItems.CGST,
                2.5D, new Money(CurrencySymbol.INR), baseGroup);
        pricingService.registerPricingLineItemWithPercentage(taxGroup, PricingLineItems.SGST,
                2.5D, new Money(CurrencySymbol.INR), baseGroup);

        // Discount
        pricingService.registerPricingLineItemWithPercentage(discountGroup, PricingLineItems.INSTANT_DISCOUNT,
                -10D, new Money(CurrencySymbol.INR), baseGroup);
        pricingService.registerPricingLineItemWithAmount(discountGroup, PricingLineItems.NEW_USER_DISCOUNT,
                new Money(-50D, CurrencySymbol.INR), baseGroup);

        Pricing pricing = new Pricing();
        pricing.addPricingGroup(baseGroup);
        pricing.addPricingGroup(taxGroup);
        pricing.addPricingGroup(discountGroup);

        ProductVarietyMetadata varietyMetadata = new ProductVarietyMetadata();

        // Product variety specific metadata
        varietyMetadata.addMetadata("Colour", "Green");
        varietyMetadata.addMetadata("RAM Memory Installed Size", "4 GB");
        varietyMetadata.addMetadata("CPU Model", "Snapdragon");
        varietyMetadata.addMetadata("Memory Storage Capacity", "128 GB");

        ProductVariety variety1 = new ProductVariety(pricing, varietyMetadata, seller);

        Product product1 = new Product(title1, desc1, List.of(variety1));

        // Top level product metadata irrespective of any variety
        product1.addMetadata("Brand", "Xiaomi");
        product1.addMetadata("Operating System", "MIUI 14, Android 13.0");

        product1.addPromise("Promise 1", "7 days Service Centre Replacement");
        product1.addPromise("Promise 2", "Free Delivery");
        product1.addPromise("Promise 3", "1 year Warranty Care");
        product1.addPromise("Promise 4", "1 year Warranty");
        product1.addPromise("Promise 5", "Pay on Delivery");
        product1.addPromise("Promise 6", "Top Brand");
        product1.addPromise("Promise 7", "Amazon Delivered");

        return product1;
    }

    public static void main(String[] args) throws PricingException {
        CustomerProfile customerProfile = new CustomerProfile(CurrencySymbol.INR); // Put addresses in the profile
        Customer customer = new Customer("test", "test@test.com", customerProfile);

        List<KV> customerPreferences = new ArrayList<>();
        customerPreferences.add(new KV(CustomerPreferenceKeys.CURRENCY, CurrencySymbol.INR));

        // Initialize dependencies here
        PricingService pricingService = new PricingServiceImpl();
        ETAService etaService = new ETAServiceImpl();
        OrderService orderService = new OrderServiceImpl(etaService);

        test1(pricingService, orderService, customer, customerPreferences);
    }
}
