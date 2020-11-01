package reflect.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenericHolder<T> {
    private T obj;

    public GenericHolder(T obj) {
        this.obj = obj;
    }
}
