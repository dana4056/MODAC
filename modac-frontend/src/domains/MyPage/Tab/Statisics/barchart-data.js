export const barChartData = {
  type: "bar",
  data: {
    labels: ["월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"],
    datasets: [{
      label: '일주일 간 TIL 작성 횟수',
      data: [1, 3, 4, 2, 1, 4, 5],
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 159, 64, 0.2)',
        'rgba(255, 205, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(201, 203, 207, 0.2)'
      ],
      borderColor: [
        'rgb(255, 99, 132)',
        'rgb(255, 159, 64)',
        'rgb(255, 205, 86)',
        'rgb(75, 192, 192)',
        'rgb(54, 162, 235)',
        'rgb(153, 102, 255)',
        'rgb(201, 203, 207)'
      ],
      borderWidth: 1
    }]
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
    },
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true,
          stepSize:1
        }
      }]
    }
  },
};




export default barChartData;