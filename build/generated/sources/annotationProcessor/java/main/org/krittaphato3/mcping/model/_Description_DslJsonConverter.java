package org.krittaphato3.mcping.model;



@javax.annotation.processing.Generated("dsl_json")
public class _Description_DslJsonConverter implements com.dslplatform.json.Configuration {
	private static final java.nio.charset.Charset utf8 = java.nio.charset.Charset.forName("UTF-8");
	@Override
	public void configure(com.dslplatform.json.DslJson __dsljson) {
		ObjectFormatConverter objectConverter = new ObjectFormatConverter(__dsljson);
		__dsljson.registerReader(org.krittaphato3.mcping.model.Description.class, objectConverter);
		__dsljson.registerWriter(org.krittaphato3.mcping.model.Description.class, objectConverter);
	}
	public final static class ObjectFormatConverter implements com.dslplatform.json.runtime.FormatConverter<org.krittaphato3.mcping.model.Description> {
		private final boolean alwaysSerialize;
		private final com.dslplatform.json.DslJson __dsljson;
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.ChatObject> reader_extra;
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.ChatObject> reader_extra() {
			if (reader_extra == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.ChatObject.class;
				reader_extra = __dsljson.tryFindReader(manifest);
				if (reader_extra == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find reader for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return reader_extra;
		}
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.ChatObject> writer_extra;
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.ChatObject> writer_extra() {
			if (writer_extra == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.ChatObject.class;
				writer_extra = __dsljson.tryFindWriter(manifest);
				if (writer_extra == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find writer for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return writer_extra;
		}
		public ObjectFormatConverter(com.dslplatform.json.DslJson __dsljson) {
			this.alwaysSerialize = !__dsljson.omitDefaults;
			this.__dsljson = __dsljson;
		}
		private static final byte[] quoted_text = "\"text\":".getBytes(utf8);
		private static final byte[] name_text = "text".getBytes(utf8);
		private static final byte[] quoted_extra = ",\"extra\":".getBytes(utf8);
		private static final byte[] name_extra = "extra".getBytes(utf8);
		public final void write(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.Description instance) {
			if (instance == null) writer.writeNull();
			else {
				writer.writeByte((byte)'{');
				if (alwaysSerialize) { writeContentFull(writer, instance); writer.writeByte((byte)'}'); }
				else if (writeContentMinimal(writer, instance)) writer.getByteBuffer()[writer.size() - 1] = '}';
				else writer.writeByte((byte)'}');
			}
		}
		public void writeContentFull(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.Description instance) {
			writer.writeAscii(quoted_text);
			if (instance.getText() == null) writer.writeNull();
			else com.dslplatform.json.StringConverter.serialize(instance.getText(), writer);
			writer.writeAscii(quoted_extra);
			if (instance.getExtra() == null) writer.writeNull();
			else writer.serialize(instance.getExtra(), writer_extra());
		}
		public boolean writeContentMinimal(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.Description instance) {
			boolean hasWritten = false;
			if (instance.getText() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_text); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.StringConverter.serialize(instance.getText(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.getExtra() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_extra); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				writer.serialize(instance.getExtra(), writer_extra());
				writer.writeByte((byte)','); hasWritten = true;
			}
			return hasWritten;
		}
		public org.krittaphato3.mcping.model.Description read(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			if (reader.wasNull()) return null;
			else if (reader.last() != '{') throw reader.newParseError("Expecting '{' for object start");
			reader.getNextToken();
			return readContent(reader);
		}
		public org.krittaphato3.mcping.model.Description readContent(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			java.lang.String _text_ = null;
			java.util.List<org.krittaphato3.mcping.model.ChatObject> _extra_ = null;
			if (reader.last() == '}') {
				return new org.krittaphato3.mcping.model.Description(_text_, _extra_);
			}
			switch(reader.fillName()) {
				case -1466950167:
					reader.getNextToken();
					_extra_ = reader.readCollection(reader_extra());
					reader.getNextToken();
					break;
				case -1108980162:
					reader.getNextToken();
					_text_ = com.dslplatform.json.StringConverter.READER.read(reader);
					reader.getNextToken();
					break;
				default:
					reader.getNextToken();
					reader.skip();
			}
			while (reader.last() == ','){
				reader.getNextToken();
				switch(reader.fillName()) {
					case -1466950167:
						reader.getNextToken();
						_extra_ = reader.readCollection(reader_extra());
						reader.getNextToken();
						break;
					case -1108980162:
						reader.getNextToken();
						_text_ = com.dslplatform.json.StringConverter.READER.read(reader);
						reader.getNextToken();
						break;
					default:
						reader.getNextToken();
						reader.skip();
				}
			}
			if (reader.last() != '}') throw reader.newParseError("Expecting '}' for object end");
			return new org.krittaphato3.mcping.model.Description(_text_, _extra_);
		}
	}
}
