package microservices.book.impl.services.persistent;

import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UUIDConverter implements AttributeConverter<UUID, UUID> {

  @Override
  public UUID convertToDatabaseColumn(UUID arg0) {
    return arg0;
  }

  @Override
  public UUID convertToEntityAttribute(UUID arg0) {
    return arg0;
  }
}
