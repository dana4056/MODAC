export const barChartData = {
  type: "radar",
  data: {
    labels: ["알고리즘", "개발", "CS", "면접", "기타"],
    datasets: [
      {
        label: "카테고리별 작성 횟수",
        data: [5, 0, 2, 4, 7],
        fill: true,
        backgroundColor: "rgb(54, 162, 235, 0.2)",
        borderColor: "rgb(54, 162, 235)",
        borderWidth: 1,
        pointBackgroundColor: "rgb(133, 105, 241)",
        // pointBorderColor: "#fff",
        pointHoverBackgroundColor: "#fff",
        pointHoverBorderColor: "rgb(133, 105, 241)",
      }
    ]
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
    },
    scale: {
      ticks: {
        suggestedMin: 0,
        // suggestedMax: 20
      }
    }
  },
};




export default barChartData;