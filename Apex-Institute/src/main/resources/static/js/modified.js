jQuery.noConflict();
jQuery(document).ready(function() {
	jQuery(".sidenav").sidenav();
});

//slider

const slider = document.querySelector(".slider");
M.Slider.init(slider, {
	indicators: false,
	transition: 500,
	height: 500,
	interval: 2000,
});

// material box

const mb = document.querySelectorAll(".materialboxed");
M.Materialbox.init(mb, {});

// scroll spy

const ss = document.querySelectorAll(".scrollspy");
M.ScrollSpy.init(ss, {
	scrollOffset: 50,
});

// Floating Button

jQuery(document).ready(function() {
	jQuery(".fixed-action-btn").floatingActionButton();
});

// Tooltip

jQuery(document).ready(function() {
	jQuery(".tooltipped").tooltip();
});

// Select

jQuery(document).ready(function() {
	jQuery("select").formSelect();
});

// Sidenav

jQuery(document).ready(function() {
	jQuery(".sidenav-fixed").sidenav();
});

// Dropdown

jQuery(".dropdown-trigger").dropdown();

// Collapsible

jQuery(document).ready(function() {
	jQuery(".collapsible").collapsible();
});

// DataTables
if (window.location.pathname == "/admin/**") {
	jQuery(document).ready(function() {
		jQuery("#example").DataTable({
			paging: false,
			bFilter: false,
			ordering: true,
			searching: true,
			dom: "t",
		});
		jQuery("select").formSelect();
	});
}
// date picker

document.addEventListener("DOMContentLoaded", function() {
	var elems = document.querySelectorAll(".datepicker");
	var instances = M.Datepicker.init(elems, {
		setDefaultDate: true,
		format: 'mmm-dd-yyyy',
		autoClose: true,
		defaultDate: new Date(),
	});
});

if (window.location.pathname == "/takeAttendance") {
	jQuery(document).ready(function() {
		var rowCount = jQuery('table >tbody >tr').length;
		jQuery('input[type=radio]').addClass("with-gap");
		updateCount();
		jQuery('#all__absent').click(function() {
			jQuery('.absent').prop('checked', true);
			jQuery('tbody input').attr('disabled', false);
			updateCount();
		})

		// OR
		jQuery('#all__present').click(function() {
			jQuery('.present').prop('checked', true);
			jQuery('tbody input').attr('disabled', false);
			updateCount();
		})

		jQuery('#holiday').click(function() {
			updateCount();
			jQuery('tbody input').prop('checked', false);
			jQuery('tbody input').attr('disabled', true);
			jQuery('.total').html('--');
			jQuery('.present--count').html('--');
			jQuery('.absent--count').html('--');
		})

		jQuery('tbody input').click(function() {
			updateCount();
		})

		function updateCount() {
			jQuery('.total').html(rowCount);
			jQuery('.absent--count').html(jQuery('.absent:checked').length);
			jQuery('.present--count').html(jQuery('.present:checked').length);
		}

	})
}

/* Student home page */

function openSidenav() {
	document.getElementById("openSidenav").style.width = '300px';
}
function closeSidenav() {
	document.getElementById("openSidenav").style.width = null;
}

jQuery(document).mouseup(function(e) {
	var container = document.getElementById("openSidenav");

	// if the target of the click isn't the container nor a descendant of the container
	if ((jQuery(e.target).closest("openSidenav").length === 0) && jQuery(document).width() < 1020) {
		closeSidenav();
	}
});

function show_notification(notification) {
	var vn = document.getElementById(notification);
	var nb;
	if (notification === "viewNotification-1") {
		nb = document.getElementsByClassName("notification-content")[0];
	}
	else if (notification === "viewNotification-2") {
		nb = document.getElementsByClassName("notification-content")[1];
	}
	else {
		nb = document.getElementsByClassName("notification-content")[2];
	}
	if (nb.style.height) {
		vn.innerHTML = "View";
		nb.style.height = null;
	}
	else {
		nb.style.height = nb.scrollHeight + "px";
		vn.innerHTML = "Hide";
	}

}

/*------------File size validation----------------*/

Filevalidation = () => {
	const fi = document.getElementById('file');
	// Check if any file is selected. 
	if (fi.files.length > 0) {
		for (var i = 0; i <= fi.files.length - 1; i++) {

			const fsize = fi.files.item(i).size;
			const file = Math.round((fsize / 1024));
			// The size of the file. 
			if (file >= 4096) {
				document.getElementsByClassName('helper-text')[0].innerHTML = "File too Big, please select a file less than 4mb";
				document.getElementById('fileButton').disabled = true;
				document.getElementById('validate').classList.add('invalid')
			}
			else {
				document.getElementsByClassName('helper-text')[0].innerHTML = "";
				document.getElementById('fileButton').disabled = false;
				document.getElementById('validate').classList.remove('invalid')
				document.getElementById('validate').classList.add('valid')
			}
		}
	}
}

/*if (window.location.pathname == "/takeAttendance") {
	var formData = new FormData(attendanceForm);
	formData.delete("_csrf");
	formData.delete("data");
	var object = {};
	var json;

	formData.forEach(function(value, name) {
		object[name] = value;
	});
	json = JSON.stringify(object);
	console.log(json);
}*/

function saveAttendance() {
	var formData = new FormData(attendanceForm);
	formData.delete("_csrf");
	formData.delete("data");
	formData.delete("date");
	formData.delete("batch_id");
	var students = [];
	/*formData.forEach(function(key, value) {
		students[key] = value;
	});*/

	for (var pair of formData.entries()) {
		var obj = {};
		obj["id"] = pair[0];
		obj["status"] = pair[1];
		students.push(obj);
	}

	var json = JSON.stringify(students);
	document.getElementById("data").value = json;
	document.getElementById("date").value = document.getElementById("currentDate").value;
	document.attendanceForm.submit();
}

function updateAttendance(){
	var obj = JSON.parse(document.getElementById("attendance").value);
		const monthNames = ["January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December"
		];
		var attendance = [];
		for (var k in obj) {
			attend = {
				"date": convertDate(k),
				"status": obj[k]
			}
			attendance.push(attend);
		}
		for (var k = 0; k < attendance.length; k++) {
			var dateArray = attendance[k].date.split('-');
			var year = dateArray[0];
			var month = monthNames[dateArray[1] - 1];
			var day = dateArray[2];
			var pageYear = document.getElementById('year').innerHTML;
			var pageMonth = document.getElementById('month').innerHTML;
			for (var i = 0; i < attendance.length; i++) {
				if (pageYear == year && pageMonth == month) {
					var days = document.getElementsByClassName('day');
					for (var j = 0; j < days.length; j++) {
						var pageDay = days[j];
						if (pageDay.innerHTML == day) {
							pageDay.classList.add("is_"+attendance[k].status)
						}
					}
				}
			}
		}
}

function convertDate(date) {
	var c = date.replace(/\b0/g, '');
	var d = c;
	return d.split(' ')[0];
}

/*student profile*/

if(window.location.pathname == "/viewStudentProfile"){
	
        $(".edit-icon").click(function (e) {
            $("#file").click();
        });
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#profile_pic')
                .attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

