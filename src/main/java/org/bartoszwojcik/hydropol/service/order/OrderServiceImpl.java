package org.bartoszwojcik.hydropol.service.order;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.mapper.OrderMapper;
import org.bartoszwojcik.hydropol.repository.order.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderDto> findInProgress(Pageable pageable) {
        return orderRepository.findByOrderStatus("In progress",pageable)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto modifyOrderStatus(String status) {
        return null;
    }

    @Override
    public OrderDto modifyUserNameInOrder(String name) {
        return null;
    }

    @Override
    public OrderDto modifyTelephoneNumberInOrder(Integer telephoneNumber) {
        return null;
    }

    @Override
    public OrderDto modifyCityInOrder(String city) {
        return null;
    }

    @Override
    public OrderDto modifyPostalCodeInOrder(String postalCode) {
        return null;
    }

    @Override
    public OrderDto modifyHouseNumberInOrder(String houseNumber) {
        return null;
    }

    @Override
    public OrderDto modifyDefectDifficultyInOrder(String defectDifficulty) {
        return null;
    }

    @Override
    public OrderDto modifyDescriptionInOrder(String description) {
        return null;
    }

    @Override
    public OrderDto modifyAssignedToInOrder(Integer assignedTo) {
        return null;
    }

    @Override
    public OrderDto modifyPriceInOrder(Integer price) {
        return null;
    }

    @Override
    public OrderDto modifyClientResponseInOrder(String clientResponse) {
        return null;
    }

    @Override
    public OrderDto modifyEmailInOrder(String email) {
        return null;
    }

    @Override
    public OrderDto modifyPaymentMethodInOrder(String paymentMethod) {
        return null;
    }

    @Override
    public OrderDto modifySalesDocumentInOrder(String salesDocument) {
        return null;
    }

    @Override
    public OrderDto modifyUrgencyInOrder(String urgency) {
        return null;
    }

    @Override
    public OrderDto modifyBillingNameInOrder(String billingName) {
        return null;
    }

    @Override
    public OrderDto modifyBillingAddressInOrder(String billingAddress) {
        return null;
    }

    @Override
    public OrderDto modifyBillingCityInOrder(String billingCity) {
        return null;
    }

    @Override
    public OrderDto modifyBillingPostalCodeInOrder(String billingPostalCode) {
        return null;
    }

    @Override
    public OrderDto modifyBillingCountryInOrder(String billingCountry) {
        return null;
    }

    @Override
    public OrderDto modifyBillingPhoneInOrder(String billingPhone) {
        return null;
    }

    @Override
    public OrderDto modifyBillingTaxIdInOrder(String billingTaxId) {
        return null;
    }

}
