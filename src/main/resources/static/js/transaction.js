$(document).ready(function() {
		loadData();
	});

	function loadData() {
		$.ajax({
			url : '/reporting/api/transaction/getList',
			method : 'GET',
			dataType : 'json',
			success : function(data) {
				// Data JSON berhasil diambil, masukkan ke dalam tabel
				var table = $('#table_transaction').DataTable({
					dom : 'Bfrtip',
					buttons : [ {
						text : 'Upload Data',
						className : 'btn btn-info',
						action : function(e, dt, node, config) {
							$('#staticBackdrop').modal('show');
							$.ajax({
								url : 'formUpload',
								type : 'get',
								dataType : 'html',
								success : function(result) {
									$('#modal-main').html(result);
									$('#btn_upload').css("display", "block");
									$('#btn_sample').css("display", "none");
									
								}
							});
							
						}
					},{
						text : 'Rule',
						className : 'btn btn-secondary',
						action : function(e, dt, node, config) {
							$('#staticBackdrop').modal('show');
							$.ajax({
								url : 'rule',
								type : 'get',
								dataType : 'html',
								success : function(result) {
									$('#modal-main').html(result);
									$('#btn_upload').css("display", "none");
									$('#btn_sample').css("display", "block");
								}
							});
						}
					},{
						text : 'Clear Data',
						className : 'btn btn-danger',
						action : function(e, dt, node, config) { $.ajax({
		                    type: "DELETE",
		                    url: "/reporting/api/transaction/clear",
		                    success: function (response) {
		                    	if(response=="00"){
									swal("Success", "Data berhasil dihapus!", "success");
									transaction();
		                    	}else{
									swal("Failed!", "Data gagal dihapus!", "error");
								}
		                    },
		                    error: function (error) {
		                        console.error(error);
		                        swal("Failed!", error, "error");
		                 
		                    }
		                });
						}
					} ],
					data : data,
					columns : [ {
						data : 'noTransaction'
					}, {
						data : 'merchant.merchantName'
					}, {
						data : 'transactionTime'
					}, {
						data : 'staff.staffName'
					}, {
						data : 'payAmount',
						render: function(data, type, row) {
			                if (type === 'display' || type === 'filter') {
			                    return formatCurrency(data);
			                }
			                return data;
			            }
					}, {
						data : 'payment.paymentName'
					}, {
						data : 'customer.customerName'
					}, {
						data : 'tax'
					}, {
						data : 'changeAmount',
						render: function(data, type, row) {
			                if (type === 'display' || type === 'filter') {
			                    // Format the currency for display and filtering
			                    return formatCurrency(data);
			                }
			                return data;
			            }
					}, {
						data : 'totalAmount',
						render: function(data, type, row) {
			                if (type === 'display' || type === 'filter') {
			                    // Format the currency for display and filtering
			                    return formatCurrency(data);
			                }
			                return data;
			            }
					}, {
						data : 'paymentStatus',
				        render: function(data, type, row) {
				        	var label;
				        	if(data=="Paid"){
				        		label = "success";
				        	}else{
				        		label = "secondary";
				        	}
				            return '<span class="badge bg-'+label+'">' + data + '</span>';
				        }
					} ]
				});
			},
			error : function(error) {
				console.error('Error:', error);
			}
		});
	}
	
	function formatCurrency(amount) {
	    const formattedAmount = new Intl.NumberFormat('id-ID', {
	        style: 'currency',
	        currency: 'IDR'
	    }).format(amount);

	    return formattedAmount.replace(/\,00$/, '');
	}

function uploadFile() {
	let formData = new FormData();
	formData.append("file", file.files[0]);
	
	var fileName = $("#file").val();

    // Cek apakah input file kosong
    if (!fileName) {
    	swal("Warning!", "File belum dipilih!", "warning");
        return false;
    }

    // Cek ekstensi file
    var fileExtension = fileName.split('.').pop().toLowerCase();
    if (fileExtension !== 'txt') {
        swal("Warning!", "Hanya file dengan format .txt yang diperbolehkan.", "warning");
        return false;
    }

		$.ajax({
			url : 'upload',
			cache : false,
			contentType : false,
			processData : false,
			data : formData,
			type : 'post',
			async : true,
			cache : false,
			success : function(response) {
				if (response == 'CREATED') {
					$('#staticBackdrop').modal('hide');
					swal("Success", "Data berhasil disimpan", "success");
					transaction();
				} else {
					swal("Failed", response, "error");
				}
			}
		});
}

function downloadFileSample(){
	 	var fileName = "data_sample.txt"; // Ganti dengan nama file yang sesuai
	    var downloadUrl = "/reporting/api/transaction/sample/" + fileName;

	    // Membuat elemen <a> untuk mengunduh file
	    var a = document.createElement('a');
	    a.href = downloadUrl;
	    a.download = fileName;
	    a.style.display = 'none';
	    document.body.appendChild(a);
	    a.click();
	    document.body.removeChild(a);
}