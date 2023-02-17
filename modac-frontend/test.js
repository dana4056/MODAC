const originalTodos = [{todos_seq: 1, status: 1}, {todos_seq: 1, status: 2}]

// 1. filter 사용해서 각 객체의 status가 2인 것인 새로운 배열 만들기

const filterTodos1 = originalTodos.filter(todo => todo.status === 2);

const filterTodos2 = originalTodos.filter((elem) => { 
    console.log(elem)
    return elem.status === 2
})
console.log('filterTodos1', filterTodos1)

// 2. 내가 추가로 넣고 싶은 데이터를 넣기

const newTodos = filterTodos1.map((elem) => {
  elem.isSubmit = false
  elem.public_type = 0
  return elem
})
console.log('newTodos', newTodos)