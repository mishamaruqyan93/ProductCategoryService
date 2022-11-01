package am.itspace.productcategoryservice.exception;

public class DeleteProductProcessingException extends RuntimeException {

    public DeleteProductProcessingException(String errorMsg) {
        super(errorMsg);
    }
}
