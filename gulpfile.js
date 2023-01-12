var gulp = require('gulp');

gulp.task('watch:scss', function () {
    gulp.watch('ui.frontend\src\main\webpack\components/*.scss');
});