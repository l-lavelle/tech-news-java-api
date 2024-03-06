const postTitle= document.querySelector('#edit-title');
const postText= document.querySelector('#edit-postText');

async function editPostHandler(event) {
  event.preventDefault();

  const id = window.location.toString().split('/')[
    window.location.toString().split('/').length - 1
  ];

  const response = await fetch(`/api/posts/${id}`, {
    method: 'PUT',
    body: JSON.stringify({
        title: postTitle.value,
        postText: postText.value
    }),
    headers: {
      'Content-Type': 'application/json'
    }
  });

  if (response.ok) {
    document.location.replace("/dashboard");
  } else {
    alert(response.statusText);
  }
}

document.querySelector('.edit-post').addEventListener('click', editPostHandler);
