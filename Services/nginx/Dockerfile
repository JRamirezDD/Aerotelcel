# Use the official NGINX image as a base
FROM nginx:alpine

# Remove the default NGINX configuration file
RUN rm /etc/nginx/conf.d/default.conf

# Copy the configuration file from the current directory to the container, specifying the destination filename
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80 to the host
EXPOSE 80
