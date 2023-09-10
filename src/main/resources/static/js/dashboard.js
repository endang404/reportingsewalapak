function search() {
		// URL endpoint untuk mengambil data
		var search = $('#search').val();
		var url = '/reporting/api/transaction/getData/' + search;

		// Menggunakan jQuery untuk membuat permintaan AJAX
		$.ajax({
					url : url,
					type : 'GET',
					dataType : 'json',
					success : function(data) {
						// Handle data yang diterima dari server di sini
						console.log('Data yang diterima:', data.paymentStatus);
						if (data.paymentStatus == "Paid") {
							swal("Payment Paid", "Transaksi sudah dibayar",
									"success");
						}
						if (data.paymentStatus == "Unpaid") {
							swal("Payment Unpaid", "Transaksi belum dibayar",
									"warning");
						}

					},
					error : function(error) {
						// Handle error jika terjadi kesalahan dalam permintaan
						swal("Not Found", "No Transaksi tidak ditemukan",
								"error");
						console.error('Kesalahan:', error);
					}
				});
	}