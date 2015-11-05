var gulp = require('gulp');
var bower =require('gulp-bower');
var minifyCss = require('gulp-minify-css');
var rename = require('gulp-rename');
var minify = require('gulp-minify');

gulp.task('bower', function() {
   return bower();
});

gulp.task('minify-css', function() {
   return gulp.src('web-ui/src/main/resources/static/css/*.css')
       .pipe(minifyCss())
       .pipe(rename({suffix: '-min'}))
       .pipe(gulp.dest('web-ui/src/main/resources/static/css/'));
});

gulp.task('compress', function() {
    gulp.src('web-ui/src/main/resources/static/js/*.js')
        .pipe(minify({
            ignoreFiles: ['*-min.js']
        }))
        .pipe(gulp.dest('web-ui/src/main/resources/static/js/'))
});

gulp.task('default', ['bower','minify-css','compress']);