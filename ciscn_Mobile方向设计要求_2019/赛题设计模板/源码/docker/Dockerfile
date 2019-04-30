FROM wnameless/mysql-phpmyadmin:latest
RUN rm -rf /var/www/
RUN apt-get install -y curl
COPY ./www /var/www
COPY ./flag /flag
WORKDIR /var/www/