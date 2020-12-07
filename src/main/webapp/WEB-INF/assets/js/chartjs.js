	
$(function () {
    new Chart(document.getElementById("bar_chart").getContext("2d"), getChartJs('bar'));
    new Chart(document.getElementById("pie_chart").getContext("2d"), getChartJs('pie'));
    
});

function getChartJs(type) {
    var config = null;

    if (type === 'bar') {
        config = {
            type: 'bar',
            data: {
                labels: ["January", "Febryary", "March", "April", "May", "June", "July"],
                datasets: [{
                    label: "Payment",
                    data: [65, 59, 80, 81, 59, 80, 81],
                    backgroundColor: 'rgba(0, 188, 212, 0.8)'
                }]
            },
            options: {
                responsive: true,
                legend: false
            }
        }
    }else if (type === 'pie') {
        config = {
                type: 'pie',
                data: {
                    datasets: [{
                        data: [225, 50, 100, 40,12],
                        backgroundColor: [
                            "rgb(233, 30, 99)",
                            "rgb(255, 193, 7)",
                            "rgb(0, 188, 212)",
                            "rgb(139, 195, 74)",
                            "rgb(139, 195, 74)"
                        ],
                    }],
                    labels: [
                        "Raw Materials",
                        "Finished Products",
                        "Packaging Materials",
                        "PT Sample",
                        "Analyst Validation"
                    ]
                },
                options: {
                    responsive: true,
                    legend: false
                }
            }
        }
    
   
    return config;
}