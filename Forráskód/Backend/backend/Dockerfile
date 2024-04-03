FROM amazoncorretto:17
LABEL authors="otottkovi"
ENV JWT_SECRET="WFPo9H2ya96sgc71EwZrekD3"
ENV DATABASE_URL="postgres://postgres:@localhost:5432/boola"
ENV PORT=8080
WORKDIR ~/
COPY build/distributions/backend-0.0.1.zip ./
RUN unzip backend-0.0.1.zip -d backend
WORKDIR backend/bin
RUN chmod +x backend
ENTRYPOINT backend