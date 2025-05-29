package org.bartoszwojcik.hydropol.service.order;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    List<OrderDto> findAll(Pageable pageable);

    List<OrderDto> findInProgress(Pageable pageable);

    OrderDto modifyOrderStatus(String status);

    OrderDto modifyUserNameInOrder(String name);

    OrderDto modifyTelephoneNumberInOrder(Integer telephoneNumber);

    OrderDto modifyCityInOrder(String city);

    OrderDto modifyPostalCodeInOrder(String postalCode);

    OrderDto modifyHouseNumberInOrder(String houseNumber);

    OrderDto modifyDefectDifficultyInOrder(String defectDifficulty);

    OrderDto modifyDescriptionInOrder(String description);

    OrderDto modifyAssignedToInOrder(Integer assignedTo);

    OrderDto modifyPriceInOrder(Integer price);

    OrderDto modifyClientResponseInOrder(String clientResponse);

    OrderDto modifyEmailInOrder(String email);

    OrderDto modifyPaymentMethodInOrder(String paymentMethod);

    OrderDto modifySalesDocumentInOrder(String salesDocument);

    OrderDto modifyUrgencyInOrder(String urgency);

    OrderDto modifyBillingNameInOrder(String billingName);

    OrderDto modifyBillingAddressInOrder(String billingAddress);

    OrderDto modifyBillingCityInOrder(String billingCity);

    OrderDto modifyBillingPostalCodeInOrder(String billingPostalCode);

    OrderDto modifyBillingCountryInOrder(String billingCountry);

    OrderDto modifyBillingPhoneInOrder(String billingPhone);

    OrderDto modifyBillingTaxIdInOrder(String billingTaxId);

}
