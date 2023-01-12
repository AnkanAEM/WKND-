/* const prev = document.getElementsByClassName("prev");
const next = document.getElementsByClassName("next");

const dot = document.getElementsByClassName("dot");

let slideIndex = 1;

prev[0].addEventListener('click', plusSlides(-1));
next[0].addEventListener('click', plusSlides(1));

for(let i=0; i<dot.length;i++ ){

  dot[i].addEventListener('click',currentSlide(i));

}


showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
  console.log("Hi")
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
} */