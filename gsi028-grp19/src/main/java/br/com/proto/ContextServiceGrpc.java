package br.com.proto;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: ContextProto.proto")
public final class ContextServiceGrpc {

  private ContextServiceGrpc() {}

  public static final String SERVICE_NAME = "br.com.proto.ContextService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<br.com.proto.ContextProto.ContextRequest,
      br.com.proto.ContextProto.ContextResponse> METHOD_INSERT =
      io.grpc.MethodDescriptor.<br.com.proto.ContextProto.ContextRequest, br.com.proto.ContextProto.ContextResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "br.com.proto.ContextService", "insert"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.ContextRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.ContextResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ContextServiceMethodDescriptorSupplier("insert"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<br.com.proto.ContextProto.ContextRequest,
      br.com.proto.ContextProto.ContextResponse> METHOD_UPDATE =
      io.grpc.MethodDescriptor.<br.com.proto.ContextProto.ContextRequest, br.com.proto.ContextProto.ContextResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "br.com.proto.ContextService", "update"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.ContextRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.ContextResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ContextServiceMethodDescriptorSupplier("update"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<br.com.proto.ContextProto.ContextRequest,
      br.com.proto.ContextProto.ContextResponse> METHOD_DELETE =
      io.grpc.MethodDescriptor.<br.com.proto.ContextProto.ContextRequest, br.com.proto.ContextProto.ContextResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "br.com.proto.ContextService", "delete"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.ContextRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.ContextResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ContextServiceMethodDescriptorSupplier("delete"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<br.com.proto.ContextProto.ContextRequest,
      br.com.proto.ContextProto.ContextResponse> METHOD_FIND =
      io.grpc.MethodDescriptor.<br.com.proto.ContextProto.ContextRequest, br.com.proto.ContextProto.ContextResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "br.com.proto.ContextService", "find"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.ContextRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.ContextResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ContextServiceMethodDescriptorSupplier("find"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<br.com.proto.ContextProto.SubscribeRequest,
      br.com.proto.ContextProto.SubscribeResponse> METHOD_SUBSCRIBE =
      io.grpc.MethodDescriptor.<br.com.proto.ContextProto.SubscribeRequest, br.com.proto.ContextProto.SubscribeResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "br.com.proto.ContextService", "subscribe"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.SubscribeRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              br.com.proto.ContextProto.SubscribeResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ContextServiceMethodDescriptorSupplier("subscribe"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ContextServiceStub newStub(io.grpc.Channel channel) {
    return new ContextServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ContextServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ContextServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ContextServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ContextServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ContextServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void insert(br.com.proto.ContextProto.ContextRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_INSERT, responseObserver);
    }

    /**
     */
    public void update(br.com.proto.ContextProto.ContextRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE, responseObserver);
    }

    /**
     */
    public void delete(br.com.proto.ContextProto.ContextRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE, responseObserver);
    }

    /**
     */
    public void find(br.com.proto.ContextProto.ContextRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_FIND, responseObserver);
    }

    /**
     */
    public void subscribe(br.com.proto.ContextProto.SubscribeRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.SubscribeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SUBSCRIBE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_INSERT,
            asyncUnaryCall(
              new MethodHandlers<
                br.com.proto.ContextProto.ContextRequest,
                br.com.proto.ContextProto.ContextResponse>(
                  this, METHODID_INSERT)))
          .addMethod(
            METHOD_UPDATE,
            asyncUnaryCall(
              new MethodHandlers<
                br.com.proto.ContextProto.ContextRequest,
                br.com.proto.ContextProto.ContextResponse>(
                  this, METHODID_UPDATE)))
          .addMethod(
            METHOD_DELETE,
            asyncUnaryCall(
              new MethodHandlers<
                br.com.proto.ContextProto.ContextRequest,
                br.com.proto.ContextProto.ContextResponse>(
                  this, METHODID_DELETE)))
          .addMethod(
            METHOD_FIND,
            asyncUnaryCall(
              new MethodHandlers<
                br.com.proto.ContextProto.ContextRequest,
                br.com.proto.ContextProto.ContextResponse>(
                  this, METHODID_FIND)))
          .addMethod(
            METHOD_SUBSCRIBE,
            asyncServerStreamingCall(
              new MethodHandlers<
                br.com.proto.ContextProto.SubscribeRequest,
                br.com.proto.ContextProto.SubscribeResponse>(
                  this, METHODID_SUBSCRIBE)))
          .build();
    }
  }

  /**
   */
  public static final class ContextServiceStub extends io.grpc.stub.AbstractStub<ContextServiceStub> {
    private ContextServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ContextServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContextServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ContextServiceStub(channel, callOptions);
    }

    /**
     */
    public void insert(br.com.proto.ContextProto.ContextRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_INSERT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(br.com.proto.ContextProto.ContextRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(br.com.proto.ContextProto.ContextRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void find(br.com.proto.ContextProto.ContextRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FIND, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribe(br.com.proto.ContextProto.SubscribeRequest request,
        io.grpc.stub.StreamObserver<br.com.proto.ContextProto.SubscribeResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_SUBSCRIBE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ContextServiceBlockingStub extends io.grpc.stub.AbstractStub<ContextServiceBlockingStub> {
    private ContextServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ContextServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContextServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ContextServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public br.com.proto.ContextProto.ContextResponse insert(br.com.proto.ContextProto.ContextRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_INSERT, getCallOptions(), request);
    }

    /**
     */
    public br.com.proto.ContextProto.ContextResponse update(br.com.proto.ContextProto.ContextRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE, getCallOptions(), request);
    }

    /**
     */
    public br.com.proto.ContextProto.ContextResponse delete(br.com.proto.ContextProto.ContextRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE, getCallOptions(), request);
    }

    /**
     */
    public br.com.proto.ContextProto.ContextResponse find(br.com.proto.ContextProto.ContextRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_FIND, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<br.com.proto.ContextProto.SubscribeResponse> subscribe(
        br.com.proto.ContextProto.SubscribeRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_SUBSCRIBE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ContextServiceFutureStub extends io.grpc.stub.AbstractStub<ContextServiceFutureStub> {
    private ContextServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ContextServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContextServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ContextServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.com.proto.ContextProto.ContextResponse> insert(
        br.com.proto.ContextProto.ContextRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_INSERT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.com.proto.ContextProto.ContextResponse> update(
        br.com.proto.ContextProto.ContextRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.com.proto.ContextProto.ContextResponse> delete(
        br.com.proto.ContextProto.ContextRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.com.proto.ContextProto.ContextResponse> find(
        br.com.proto.ContextProto.ContextRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FIND, getCallOptions()), request);
    }
  }

  private static final int METHODID_INSERT = 0;
  private static final int METHODID_UPDATE = 1;
  private static final int METHODID_DELETE = 2;
  private static final int METHODID_FIND = 3;
  private static final int METHODID_SUBSCRIBE = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ContextServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ContextServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INSERT:
          serviceImpl.insert((br.com.proto.ContextProto.ContextRequest) request,
              (io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((br.com.proto.ContextProto.ContextRequest) request,
              (io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((br.com.proto.ContextProto.ContextRequest) request,
              (io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse>) responseObserver);
          break;
        case METHODID_FIND:
          serviceImpl.find((br.com.proto.ContextProto.ContextRequest) request,
              (io.grpc.stub.StreamObserver<br.com.proto.ContextProto.ContextResponse>) responseObserver);
          break;
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((br.com.proto.ContextProto.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<br.com.proto.ContextProto.SubscribeResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ContextServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ContextServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return br.com.proto.ContextProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ContextService");
    }
  }

  private static final class ContextServiceFileDescriptorSupplier
      extends ContextServiceBaseDescriptorSupplier {
    ContextServiceFileDescriptorSupplier() {}
  }

  private static final class ContextServiceMethodDescriptorSupplier
      extends ContextServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ContextServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ContextServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ContextServiceFileDescriptorSupplier())
              .addMethod(METHOD_INSERT)
              .addMethod(METHOD_UPDATE)
              .addMethod(METHOD_DELETE)
              .addMethod(METHOD_FIND)
              .addMethod(METHOD_SUBSCRIBE)
              .build();
        }
      }
    }
    return result;
  }
}
