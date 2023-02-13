export const doughnutChartData = {
  type: "doughnut",
  data : {
    labels: ["알고리즘", "개발", "CS", "면접", "기타"],
    datasets: [{
      label: 'My First Dataset',
      data: [20, 20, 2, 4, 7],
      backgroundColor: [
        'rgba(255, 99, 132)',
        'rgba(255, 159, 64)',
        'rgba(255, 205, 86)',
        'rgba(75, 192, 192)',
        'rgba(54, 162, 235)',
      ],
      hoverOffset: 4
    }]
  },
};




export default doughnutChartData;