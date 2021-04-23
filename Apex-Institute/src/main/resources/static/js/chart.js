// Revenue Chart
if (window.location.pathname == "/admin" || window.location.pathname == "/teacher") {
  var ctx = document.getElementById("incomeChart").getContext("2d");
  var grd = ctx.createLinearGradient(0, 100, 0, 300);
  grd.addColorStop(0, "#b39ddb");
  grd.addColorStop(1, "white");
  var myChart = new Chart(ctx, {
    type: "line",
    data: {
      labels: [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "June",
        "July",
        "Aug",
        "Sept",
        "Oct",
        "Nov",
        "Dec",
      ],
      datasets: [
        {
          data: [100, 200, 300, 400, 200, 300, 200, 300, 600, 400, 200, 400],
          pointBorderColor: "white",

          pointBackgroundColor: "#311b92",
          backgroundColor: grd,
          borderColor: "#311b92",
          borderWidth: 2,
          pointRadius: 3.5,
        },
      ],
    },
    options: {
      responsive: true,
      // title: {
      //     display: true,
      //     text: "Income",
      //     fontSize: 20,
      //     fontColor: "black",
      //     fontFamily: 'Montserrat',

      // },
      legend: {
        display: false,
      },
      scales: {
        xAxes: [
          {
            ticks: {
              beginAtZero: true,
              padding: 10,
            },
            gridLines: {
              color: "transparent",
            },
            // scaleLabel: {
            //     display: true,
            //     labelString: 'Months'
            // }
          },
        ],
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
              padding: 10,
              stepSize: 150,
            },
            gridLines: {
              color: "lightgrey",
              // drawBorder: false
            },
            stacked: true,
            scaleLabel: {
              // display: true,
              // labelString: 'Amount'
            },
          },
        ],
      },
      animation: {
        animateScale: true,
        duration: 1000,
        easing: "linear",
      },
    },
  });
}
// Revenue Chart Ends

// student chart
if (window.location.pathname == "/admin" || window.location.pathname == "/teacher") {
	var batchNamesArray=document.getElementsByClassName("batch-name");
	var batchNames = [];
	for(var i = 0; i<batchNamesArray.length; i++){
		batchNames[i] = batchNamesArray[i].innerHTML;
	}
	
  var ctx2 = document.getElementById("studentChart").getContext("2d");
  var studentChart = new Chart(ctx2, {
    type: "doughnut",
    data: {
      datasets: [
        {
          data: [10, 20, 30, 35, 40],
          backgroundColor: [
            "#ef5350",
            "#ec407a",
            "#ab47bc",
            "#5c6bc0",
            "#42a5f5",
          ],
          borderWidth: 4,
          hoverBorderColor: "white",
          hoverBorderWidth: 1,
        },
      ],

      // These labels appear in the legend and in the tooltips when hovering different arcs
      labels: batchNames,
    },
    options: {
      responsive: true,
      title: {
        // display: true,
        // text: "Students",
        // fontSize: 20,
        // textColor: "black"
      },
      legend: {
        position: "right",
      },
      cutoutPercentage: 65,
    },
  });
}

// Student Chart Ends

// performance chart
if (window.location.pathname == "/student") {
  var ctx3 = document.getElementById("performance_chart").getContext("2d");
  var grd = ctx3.createLinearGradient(0, 100, 0, 300);
  grd.addColorStop(0, "transparent");
  grd.addColorStop(1, "transparent");
  var myChart = new Chart(ctx3, {
    type: "line",
    data: {
      labels: [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "June",
        "July",
        "Aug",
        "Sept",
        "Oct",
        "Nov",
        "Dec",
      ],
      datasets: [
        {
          data: [100, 200, 300, 400, 200, 300, 200, 300, 600, 400, 200, 400],
          pointBorderColor: "white",

          pointBackgroundColor: "#595362",
          backgroundColor: grd,
          borderColor: "#595362",
          borderWidth: 1.5,
          pointRadius: 3,
        },
      ],
    },
    options: {
      responsive: true,
      // title: {
      //     display: true,
      //     text: "Income",
      //     fontSize: 20,
      //     fontColor: "black",
      //     fontFamily: 'Montserrat',

      // },
      legend: {
        display: false,
      },
      scales: {
        xAxes: [
          {
            ticks: {
              beginAtZero: true,
              padding: 10,
            },
            gridLines: {
              color: "transparent",
            },
            // scaleLabel: {
            //     display: true,
            //     labelString: 'Months'
            // }
          },
        ],
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
              padding: 10,
              stepSize: 150,
            },
            gridLines: {
              color: "lightgrey",
              // drawBorder: false
            },
            stacked: true,
            scaleLabel: {
              // display: true,
              // labelString: 'Amount'
            },
          },
        ],
      },
      animation: {
        animateScale: true,
        duration: 1000,
        easing: "linear",
      },
    },
  });
}

// student dougnut chart

if (window.location.pathname == "/student") {
  // attendance chart
  var attendance = document.getElementById("attendance_chart").getContext("2d");
  var studentChart = new Chart(attendance, {
    type: "doughnut",
    data: {
      datasets: [
        {
          data: [60, 40],
          backgroundColor: [
            "#2AED0A",
            "white",
            "#ab47bc",
            "#5c6bc0",
            "#42a5f5",
          ],
          borderWidth: 0,
          hoverBorderColor: "white",
          hoverBorderWidth: 2,
        },
      ],

      // These labels appear in the legend and in the tooltips when hovering different arcs
      // labels: ["Attendance"],
    },
    options: {
      responsive: true,
      maintainAspectRatio: true,
      title: {
        // display: true,
        // text: "Students",
        // fontSize: 20,
        // textColor: "black"
      },
      // legend: {
      //   position: "right",
      // },
      cutoutPercentage: 75,
    },
  });

  // assignment chart

  var assignment = document.getElementById("assignment_chart").getContext("2d");
  var studentChart = new Chart(assignment, {
    type: "doughnut",
    data: {
      datasets: [
        {
          data: [43, 57],
          backgroundColor: [
            "#0A3CED",
            "white",
            "#ab47bc",
            "#5c6bc0",
            "#42a5f5",
          ],
          borderWidth: 0,
          hoverBorderColor: "white",
          hoverBorderWidth: 2,
        },
      ],

      // These labels appear in the legend and in the tooltips when hovering different arcs
      // labels: ["Attendance"],
    },
    options: {
      responsive: true,
      title: {
        // display: true,
        // text: "Students",
        // fontSize: 20,
        // textColor: "black"
      },
      // legend: {
      //   position: "right",
      // },
      cutoutPercentage: 75,
    },
  });

  // test_chart

  var test = document.getElementById("test_chart").getContext("2d");
  var studentChart = new Chart(test, {
    type: "doughnut",
    data: {
      datasets: [
        {
          data: [90, 10],
          backgroundColor: [
            "#FFC907",
            "white",
            "#ab47bc",
            "#5c6bc0",
            "#42a5f5",
          ],
          borderWidth: 0,
          hoverBorderColor: "white",
          hoverBorderWidth: 2,
        },
      ],

      // These labels appear in the legend and in the tooltips when hovering different arcs
      // labels: ["Attendance"],
    },
    options: {
      responsive: true,
      title: {
        // display: true,
        // text: "Students",
        // fontSize: 20,
        // textColor: "black"
      },
      // legend: {
      //   position: "right",
      // },
      cutoutPercentage: 75,
    },
  });
}
