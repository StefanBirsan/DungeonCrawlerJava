package Exception;

public class ItemNonExistent extends RuntimeException {
    public ItemNonExistent(String message) {
        super(message);
    }
}
