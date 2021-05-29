let answers = [];
const quiz = document.getElementById('quiz'),
answerEls = document.querySelectorAll('.answer'),
questionEl = document.getElementById('question'),
a_text = document.getElementById('a_text'),
b_text = document.getElementById('b_text'),
c_text = document.getElementById('c_text'),
d_text = document.getElementById('d_text'),
e_text = document.getElementById('e_text'),
submitBtn = document.getElementById('submit');

let currentQuiz = 0,
  score = 0;

loadQuiz();

function loadQuiz() {
  deselectAnswers();

  const currentQuizData = quizData[currentQuiz];

  questionEl.innerText = currentQuizData.label;
  questionEl.id= currentQuizData.id;
  a_text.innerText = currentQuizData.possibilities[0].label;
  a_text.previousElementSibling.id = currentQuizData.possibilities[0].id;
  b_text.innerText = currentQuizData.possibilities[1].label;
  b_text.previousElementSibling.id = currentQuizData.possibilities[1].id;
  c_text.innerText = currentQuizData.possibilities[2].label;
  c_text.previousElementSibling.id = currentQuizData.possibilities[2].id;
  d_text.innerText = currentQuizData.possibilities[3].label;
  d_text.previousElementSibling.id= currentQuizData.possibilities[3].id;
  e_text.innerText = currentQuizData.possibilities[4].label;
  e_text.previousElementSibling.id = currentQuizData.possibilities[4].id;
}

function deselectAnswers() {
  answerEls.forEach((answerEl) => (answerEl.checked = false));
}

function getSelected() {
  let answer;
  answerEls.forEach((answerEl) => {
    if (answerEl.checked) {
      answer = answerEl.id;
    }
  });
  return answer;
}

let root = document.documentElement;
root.style.setProperty('--progress-bar', "00%");

submitBtn.addEventListener('click', () => {
  const answer = getSelected();
  if (answer) {
  	answers.push(parseInt(answer));
    currentQuiz++;
    root.style.setProperty('--progress-bar', (currentQuiz*100)/quizData.length + "%");
    if (currentQuiz < quizData.length) {
      loadQuiz();
    } else {
      	let post_url = $("#formSurvey").attr("action");
		let serialized = JSON.stringify(answers);
		$.ajax({
			url: post_url,
			dataType: 'text',
			type: 'post',
			contentType: 'application/json',
			data: serialized,
			success: function(data, textStatus, jQxhr) {
		      quiz.innerHTML = `
		        <h2>Merci d'avoir pris le temps de répondre à ce questionnaire.</h2>
		      `;
			},
			error: function(jqXhr, textStatus, errorThrown) {
		      quiz.innerHTML = `
		        <h2>${errorThrown}</h2>
		        <button onclick="location.reload()">Essayer à nouveau</button>
		      `;
			}
		});
    }
  }
});
