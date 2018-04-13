package Plugin;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface IDatabaseAccess {
    Object create(Object object);
    Object read(Object object);
    Object update(Object object);
    Object delete(Object object);
}
