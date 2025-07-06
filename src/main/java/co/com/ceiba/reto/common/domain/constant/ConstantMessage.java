package co.com.ceiba.reto.common.domain.constant;

public class ConstantMessage {
    public static final String PRODUCT_ID_IS_MANDATORY = "El id del producto es obligatorio";
    public static final String STOCK_IS_MANDATORY = "El stock es obligatorio";
    public static final String UNIT_PRICE_IS_MANDATORY = "El precio unitario es obligatorio";
    public static final String NOT_ENOUGH_STOCK = "No hay suficiente stock para el producto";
    public static final String QUANTITY_IS_MANDATORY = "La cantidad es obligatoria";
    public static final String INVALID_ITEM_QUANTITY = "La cantidad por item no puede superar 10 unidades ni ser menor a 1";
    public static final String CUSTOMER_ID_IS_MANDATORY = "El id del cliente es obligatorio";
    public static final String ITEMS_ARE_MANDATORY = "Debe agregar al menos un item al pedido";
    public static final String PAYMENT_METHOD_IS_MANDATORY = "El medio de pago es obligatorio";
    public static final String PAYMENT_METHOD_NOT_ALLOWED = "Medio de pago no permitido";
    public static final String STATUS_IS_MANDATORY = "El estado del pedido es obligatorio";

    public static final String AN_ERROR_OCCURRED_PLEASE_CONTACT_THE_ADMINISTRATOR = "An error occurred, please contact the administrator.";

    private ConstantMessage() { }
}
