package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.PaymentDto;
import app.tp136.model.Payment;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class)
public interface PaymentMapper {
    PaymentDto toDto(Payment payment);

    List<PaymentDto> toDtoList(List<Payment> payments);

    default Page<PaymentDto> toDtoPage(Page<Payment> payments) {
        List<PaymentDto> dtos = toDtoList(payments.getContent());
        return new PageImpl<>(dtos, payments.getPageable(), payments.getTotalElements());
    }
}
