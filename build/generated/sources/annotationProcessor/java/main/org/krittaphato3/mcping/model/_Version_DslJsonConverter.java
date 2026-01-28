package org.krittaphato3.mcping.model;



@javax.annotation.processing.Generated("dsl_json")
public class _Version_DslJsonConverter implements com.dslplatform.json.Configuration {
	private static final java.nio.charset.Charset utf8 = java.nio.charset.Charset.forName("UTF-8");
	@Override
	public void configure(com.dslplatform.json.DslJson __dsljson) {
		ObjectFormatConverter objectConverter = new ObjectFormatConverter(__dsljson);
		__dsljson.registerReader(org.krittaphato3.mcping.model.Version.class, objectConverter);
		__dsljson.registerWriter(org.krittaphato3.mcping.model.Version.class, objectConverter);
	}
	public final static class ObjectFormatConverter implements com.dslplatform.json.runtime.FormatConverter<org.krittaphato3.mcping.model.Version> {
		private final boolean alwaysSerialize;
		private final com.dslplatform.json.DslJson __dsljson;
		public ObjectFormatConverter(com.dslplatform.json.DslJson __dsljson) {
			this.alwaysSerialize = !__dsljson.omitDefaults;
			this.__dsljson = __dsljson;
		}
		private static final byte[] quoted_name = "\"name\":".getBytes(utf8);
		private static final byte[] name_name = "name".getBytes(utf8);
		private static final byte[] quoted_protocol = ",\"protocol\":".getBytes(utf8);
		private static final byte[] name_protocol = "protocol".getBytes(utf8);
		public final void write(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.Version instance) {
			if (instance == null) writer.writeNull();
			else {
				writer.writeByte((byte)'{');
				if (alwaysSerialize) { writeContentFull(writer, instance); writer.writeByte((byte)'}'); }
				else if (writeContentMinimal(writer, instance)) writer.getByteBuffer()[writer.size() - 1] = '}';
				else writer.writeByte((byte)'}');
			}
		}
		public void writeContentFull(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.Version instance) {
			writer.writeAscii(quoted_name);
			if (instance.getName() == null) writer.writeNull();
			else com.dslplatform.json.StringConverter.serialize(instance.getName(), writer);
			writer.writeAscii(quoted_protocol);
			com.dslplatform.json.NumberConverter.serialize(instance.getProtocol(), writer);
		}
		public boolean writeContentMinimal(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.Version instance) {
			boolean hasWritten = false;
			if (instance.getName() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_name); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.StringConverter.serialize(instance.getName(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.getProtocol() != 0) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_protocol); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.NumberConverter.serialize(instance.getProtocol(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			return hasWritten;
		}
		public org.krittaphato3.mcping.model.Version read(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			if (reader.wasNull()) return null;
			else if (reader.last() != '{') throw reader.newParseError("Expecting '{' for object start");
			reader.getNextToken();
			return readContent(reader);
		}
		public org.krittaphato3.mcping.model.Version readContent(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			java.lang.String _name_ = null;
			int _protocol_ = 0;
			if (reader.last() == '}') {
				return new org.krittaphato3.mcping.model.Version(_name_, _protocol_);
			}
			switch(reader.fillName()) {
				case -81263179:
					reader.getNextToken();
					_protocol_ = com.dslplatform.json.NumberConverter.deserializeInt(reader);
					reader.getNextToken();
					break;
				case -1925595674:
					reader.getNextToken();
					_name_ = com.dslplatform.json.StringConverter.READER.read(reader);
					reader.getNextToken();
					break;
				default:
					reader.getNextToken();
					reader.skip();
			}
			while (reader.last() == ','){
				reader.getNextToken();
				switch(reader.fillName()) {
					case -81263179:
						reader.getNextToken();
						_protocol_ = com.dslplatform.json.NumberConverter.deserializeInt(reader);
						reader.getNextToken();
						break;
					case -1925595674:
						reader.getNextToken();
						_name_ = com.dslplatform.json.StringConverter.READER.read(reader);
						reader.getNextToken();
						break;
					default:
						reader.getNextToken();
						reader.skip();
				}
			}
			if (reader.last() != '}') throw reader.newParseError("Expecting '}' for object end");
			return new org.krittaphato3.mcping.model.Version(_name_, _protocol_);
		}
	}
}
