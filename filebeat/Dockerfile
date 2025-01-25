# Use the official Filebeat image as the base image
FROM docker.elastic.co/beats/filebeat:7.17.27

# Copy the Filebeat configuration file into the container
COPY filebeat.yml /usr/share/filebeat/filebeat.yml

# Set the proper file permissions
USER root
RUN chmod go-w /usr/share/filebeat/filebeat.yml

# Set Filebeat's default command
CMD ["filebeat", "-e", "--strict.perms=false"]
