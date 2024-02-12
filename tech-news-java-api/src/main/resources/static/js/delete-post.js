async function deleteFormHandler(event) {
console.log(event)
event.preventDefault();
alert("bet asjdkdks")

  const id = window.location.toString().split('/')[
    window.location.toString().split('/').length - 1
  ];

  const response = await fetch(`/api/posts/${id}`, {
    method: 'DELE'
  });

 document.location.replace('/dashboard')
//  if (response.status===204) {
//    document.location.replace('/dashboard')
//  } else {
//    alert(response.statusText);
//  }
}

document.querySelector('.delete-post-btn').addEventListener('click', deleteFormHandler);