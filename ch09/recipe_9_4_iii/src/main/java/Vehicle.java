import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Field;

public record Vehicle(@Id String vehicleNo,
                      @Field String color,
                      @Field int wheel,
                      @Field int seat) { }
