package com.web.reporting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private Long id;

	@Id
	@Column(name = "no_transaction")
	private String noTransaction;
	
	@Column(name = "merchant_id")
	private Long merchantId;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	@Column(name = "transaction_time")
	private String transactionTime;
	
	@Column(name = "staff_id")
	private Long staffId;
		
	@Column(name = "pay_amount")
	private Long payAmount;
	
	@Column(name = "payment_id")
	private Long paymentId;
	
	@Column(name = "customer_id")
	private Long customerId;
		
	@Column(name = "tax")
	private Long tax;
	
	@Column(name = "change_amount")
	private Long changeAmount;
	
	@Column(name = "total_amount")
	private Long totalAmount;
	
	@Column(name = "payment_status")
	private String paymentStatus;
	
	@ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MerchantModel merchant;
	
	@ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id", insertable = false, updatable = false)
    private StaffModel staff;
	
	@ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PaymentModel payment;
	
	@ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CustomerModel customer;
}
