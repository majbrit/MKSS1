package de.hsbremen.mkss.events.dto;

import java.util.List;

public class OrderDTO {

    private Long id;
    private String customerName;
    private String checkoutDateTime;
    private String status;
    private int totalSum;
    private List<ItemDTO> items;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCheckoutDateTime() {
        return checkoutDateTime;
    }

    public void setCheckoutDateTime(String checkoutDateTime) {
        this.checkoutDateTime = checkoutDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
