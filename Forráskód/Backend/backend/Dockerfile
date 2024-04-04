FROM amazoncorretto:17-alpine3.16
LABEL authors="otottkovi"
ENV JWT_SECRET="WFPo9H2ya96sgc71EwZrekD3"
ENV DATABASE_URL="postgres://postgres:@localhost:5432/boola"
ENV PORT=8080
WORKDIR ~/
COPY build/distributions/backend-0.0.1.tar ./
RUN mkdir backend
RUN tar -xf backend-0.0.1.tar -C backend
WORKDIR backend/backend-0.0.1/bin
RUN chmod +x backend
ENTRYPOINT  ./backend