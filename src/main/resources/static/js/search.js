function titleSearch() {
	// Declare variables
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("titleInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("sheetTable");
	tr = table.getElementsByTagName("tr");

	// Loop through all table rows, and hide those who don't match the search query
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[1];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1 && !isHidden(tr[i])) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function composerSearch() {
	// Declare variables
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("composerInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("sheetTable");
	tr = table.getElementsByTagName("tr");

	// Loop through all table rows, and hide those who don't match the search query
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[2];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1 && !isHidden(tr[i])) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function instrumentSearch() {
	// Declare variables
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("instrumentInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("sheetTable");
	tr = table.getElementsByTagName("tr");

	// Loop through all table rows, and hide those who don't match the search query
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[3];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function isHidden(el) {
	var style = window.getComputedStyle(el);
	return ((style.display === 'none') || (style.visibility === 'hidden'))
}