package com.coder4.homs.demo;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: HomsDemo.proto")
public final class HomsDemoGrpc {

  private HomsDemoGrpc() {}

  public static final String SERVICE_NAME = "HomsDemo";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.coder4.homs.demo.HomsDemoProto.AddRequest,
      com.coder4.homs.demo.HomsDemoProto.AddResponse> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Add",
      requestType = com.coder4.homs.demo.HomsDemoProto.AddRequest.class,
      responseType = com.coder4.homs.demo.HomsDemoProto.AddResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.coder4.homs.demo.HomsDemoProto.AddRequest,
      com.coder4.homs.demo.HomsDemoProto.AddResponse> getAddMethod() {
    io.grpc.MethodDescriptor<com.coder4.homs.demo.HomsDemoProto.AddRequest, com.coder4.homs.demo.HomsDemoProto.AddResponse> getAddMethod;
    if ((getAddMethod = HomsDemoGrpc.getAddMethod) == null) {
      synchronized (HomsDemoGrpc.class) {
        if ((getAddMethod = HomsDemoGrpc.getAddMethod) == null) {
          HomsDemoGrpc.getAddMethod = getAddMethod =
              io.grpc.MethodDescriptor.<com.coder4.homs.demo.HomsDemoProto.AddRequest, com.coder4.homs.demo.HomsDemoProto.AddResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.coder4.homs.demo.HomsDemoProto.AddRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.coder4.homs.demo.HomsDemoProto.AddResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HomsDemoMethodDescriptorSupplier("Add"))
              .build();
        }
      }
    }
    return getAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.coder4.homs.demo.HomsDemoProto.AddSingleRequest,
      com.coder4.homs.demo.HomsDemoProto.AddResponse> getAdd2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Add2",
      requestType = com.coder4.homs.demo.HomsDemoProto.AddSingleRequest.class,
      responseType = com.coder4.homs.demo.HomsDemoProto.AddResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.coder4.homs.demo.HomsDemoProto.AddSingleRequest,
      com.coder4.homs.demo.HomsDemoProto.AddResponse> getAdd2Method() {
    io.grpc.MethodDescriptor<com.coder4.homs.demo.HomsDemoProto.AddSingleRequest, com.coder4.homs.demo.HomsDemoProto.AddResponse> getAdd2Method;
    if ((getAdd2Method = HomsDemoGrpc.getAdd2Method) == null) {
      synchronized (HomsDemoGrpc.class) {
        if ((getAdd2Method = HomsDemoGrpc.getAdd2Method) == null) {
          HomsDemoGrpc.getAdd2Method = getAdd2Method =
              io.grpc.MethodDescriptor.<com.coder4.homs.demo.HomsDemoProto.AddSingleRequest, com.coder4.homs.demo.HomsDemoProto.AddResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Add2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.coder4.homs.demo.HomsDemoProto.AddSingleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.coder4.homs.demo.HomsDemoProto.AddResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HomsDemoMethodDescriptorSupplier("Add2"))
              .build();
        }
      }
    }
    return getAdd2Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HomsDemoStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HomsDemoStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HomsDemoStub>() {
        @java.lang.Override
        public HomsDemoStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HomsDemoStub(channel, callOptions);
        }
      };
    return HomsDemoStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HomsDemoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HomsDemoBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HomsDemoBlockingStub>() {
        @java.lang.Override
        public HomsDemoBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HomsDemoBlockingStub(channel, callOptions);
        }
      };
    return HomsDemoBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HomsDemoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HomsDemoFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HomsDemoFutureStub>() {
        @java.lang.Override
        public HomsDemoFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HomsDemoFutureStub(channel, callOptions);
        }
      };
    return HomsDemoFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class HomsDemoImplBase implements io.grpc.BindableService {

    /**
     */
    public void add(com.coder4.homs.demo.HomsDemoProto.AddRequest request,
        io.grpc.stub.StreamObserver<com.coder4.homs.demo.HomsDemoProto.AddResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.coder4.homs.demo.HomsDemoProto.AddSingleRequest> add2(
        io.grpc.stub.StreamObserver<com.coder4.homs.demo.HomsDemoProto.AddResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getAdd2Method(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.coder4.homs.demo.HomsDemoProto.AddRequest,
                com.coder4.homs.demo.HomsDemoProto.AddResponse>(
                  this, METHODID_ADD)))
          .addMethod(
            getAdd2Method(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                com.coder4.homs.demo.HomsDemoProto.AddSingleRequest,
                com.coder4.homs.demo.HomsDemoProto.AddResponse>(
                  this, METHODID_ADD2)))
          .build();
    }
  }

  /**
   */
  public static final class HomsDemoStub extends io.grpc.stub.AbstractAsyncStub<HomsDemoStub> {
    private HomsDemoStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HomsDemoStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HomsDemoStub(channel, callOptions);
    }

    /**
     */
    public void add(com.coder4.homs.demo.HomsDemoProto.AddRequest request,
        io.grpc.stub.StreamObserver<com.coder4.homs.demo.HomsDemoProto.AddResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.coder4.homs.demo.HomsDemoProto.AddSingleRequest> add2(
        io.grpc.stub.StreamObserver<com.coder4.homs.demo.HomsDemoProto.AddResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getAdd2Method(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class HomsDemoBlockingStub extends io.grpc.stub.AbstractBlockingStub<HomsDemoBlockingStub> {
    private HomsDemoBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HomsDemoBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HomsDemoBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.coder4.homs.demo.HomsDemoProto.AddResponse add(com.coder4.homs.demo.HomsDemoProto.AddRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HomsDemoFutureStub extends io.grpc.stub.AbstractFutureStub<HomsDemoFutureStub> {
    private HomsDemoFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HomsDemoFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HomsDemoFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.coder4.homs.demo.HomsDemoProto.AddResponse> add(
        com.coder4.homs.demo.HomsDemoProto.AddRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD = 0;
  private static final int METHODID_ADD2 = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HomsDemoImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HomsDemoImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD:
          serviceImpl.add((com.coder4.homs.demo.HomsDemoProto.AddRequest) request,
              (io.grpc.stub.StreamObserver<com.coder4.homs.demo.HomsDemoProto.AddResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD2:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.add2(
              (io.grpc.stub.StreamObserver<com.coder4.homs.demo.HomsDemoProto.AddResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HomsDemoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HomsDemoBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.coder4.homs.demo.HomsDemoProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HomsDemo");
    }
  }

  private static final class HomsDemoFileDescriptorSupplier
      extends HomsDemoBaseDescriptorSupplier {
    HomsDemoFileDescriptorSupplier() {}
  }

  private static final class HomsDemoMethodDescriptorSupplier
      extends HomsDemoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HomsDemoMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HomsDemoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HomsDemoFileDescriptorSupplier())
              .addMethod(getAddMethod())
              .addMethod(getAdd2Method())
              .build();
        }
      }
    }
    return result;
  }
}
