FROM compss/compss:1.3
MAINTAINER Guilherme Maluf <guimalufb@gmail.com>

VOLUME /opt/COMPSs/Runtime/configuration

RUN ssh-keygen -t rsa -N "" -f /root/.ssh/id_rsa
RUN cp /root/.ssh/id_rsa.pub /root/.ssh/authorized_keys
RUN echo 'Host *\n  StrictHostKeyChecking no\n  UserKnownHostsFile=/dev/null' > /root/.ssh/config
VOLUME /root/.ssh/

RUN sed -i 's/PermitRootLogin without-password/PermitRootLogin yes/' /etc/ssh/sshd_config

RUN mkdir /var/workspace
VOLUME /var/workspace
WORKDIR /var/workspace

ENV JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64/
ENV PATH=$PATH:/opt/COMPSs/Runtime/scripts/user/

EXPOSE "8080" "22"

ADD entrypoint /usr/local/bin/

ENTRYPOINT ["/usr/local/bin/entrypoint"]
CMD ["/bin/bash"]
