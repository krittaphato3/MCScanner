package org.krittaphato3.mcping.model;



@javax.annotation.processing.Generated("dsl_json")
public class _ChatObject_DslJsonConverter implements com.dslplatform.json.Configuration {
	private static final java.nio.charset.Charset utf8 = java.nio.charset.Charset.forName("UTF-8");
	@Override
	public void configure(com.dslplatform.json.DslJson __dsljson) {
		ObjectFormatConverter objectConverter = new ObjectFormatConverter(__dsljson);
		__dsljson.registerReader(org.krittaphato3.mcping.model.ChatObject.class, objectConverter);
		__dsljson.registerWriter(org.krittaphato3.mcping.model.ChatObject.class, objectConverter);
	}
	public final static class ObjectFormatConverter implements com.dslplatform.json.runtime.FormatConverter<org.krittaphato3.mcping.model.ChatObject> {
		private final boolean alwaysSerialize;
		private final com.dslplatform.json.DslJson __dsljson;
		public ObjectFormatConverter(com.dslplatform.json.DslJson __dsljson) {
			this.alwaysSerialize = !__dsljson.omitDefaults;
			this.__dsljson = __dsljson;
		}
		private static final byte[] quoted_text = "\"text\":".getBytes(utf8);
		private static final byte[] name_text = "text".getBytes(utf8);
		private static final byte[] quoted_color = ",\"color\":".getBytes(utf8);
		private static final byte[] name_color = "color".getBytes(utf8);
		private static final byte[] quoted_bold = ",\"bold\":".getBytes(utf8);
		private static final byte[] name_bold = "bold".getBytes(utf8);
		public final void write(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.ChatObject instance) {
			if (instance == null) writer.writeNull();
			else {
				writer.writeByte((byte)'{');
				if (alwaysSerialize) { writeContentFull(writer, instance); writer.writeByte((byte)'}'); }
				else if (writeContentMinimal(writer, instance)) writer.getByteBuffer()[writer.size() - 1] = '}';
				else writer.writeByte((byte)'}');
			}
		}
		public void writeContentFull(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.ChatObject instance) {
			writer.writeAscii(quoted_text);
			if (instance.getText() == null) writer.writeNull();
			else com.dslplatform.json.StringConverter.serialize(instance.getText(), writer);
			writer.writeAscii(quoted_color);
			if (instance.getColor() == null) writer.writeNull();
			else com.dslplatform.json.StringConverter.serialize(instance.getColor(), writer);
			writer.writeAscii(quoted_bold);
			com.dslplatform.json.BoolConverter.serialize(instance.isBold(), writer);
		}
		public boolean writeContentMinimal(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.ChatObject instance) {
			boolean hasWritten = false;
			if (instance.getText() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_text); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.StringConverter.serialize(instance.getText(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.getColor() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_color); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.StringConverter.serialize(instance.getColor(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.isBold() != false) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_bold); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.BoolConverter.serialize(instance.isBold(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			return hasWritten;
		}
		public org.krittaphato3.mcping.model.ChatObject read(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			if (reader.wasNull()) return null;
			else if (reader.last() != '{') throw reader.newParseError("Expecting '{' for object start");
			reader.getNextToken();
			return readContent(reader);
		}
		public org.krittaphato3.mcping.model.ChatObject readContent(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			java.lang.String _text_ = null;
			java.lang.String _color_ = null;
			boolean _bold_ = false;
			if (reader.last() == '}') {
				return new org.krittaphato3.mcping.model.ChatObject(_text_, _color_, _bold_);
			}
			switch(reader.fillName()) {
				case 1031692888:
					reader.getNextToken();
					_color_ = com.dslplatform.json.StringConverter.READER.read(reader);
					reader.getNextToken();
					break;
				case -1108980162:
					reader.getNextToken();
					_text_ = com.dslplatform.json.StringConverter.READER.read(reader);
					reader.getNextToken();
					break;
				case -560531850:
					reader.getNextToken();
					_bold_ = com.dslplatform.json.BoolConverter.deserialize(reader);
					reader.getNextToken();
					break;
				default:
					reader.getNextToken();
					reader.skip();
			}
			while (reader.last() == ','){
				reader.getNextToken();
				switch(reader.fillName()) {
					case 1031692888:
						reader.getNextToken();
						_color_ = com.dslplatform.json.StringConverter.READER.read(reader);
						reader.getNextToken();
						break;
					case -1108980162:
						reader.getNextToken();
						_text_ = com.dslplatform.json.StringConverter.READER.read(reader);
						reader.getNextToken();
						break;
					case -560531850:
						reader.getNextToken();
						_bold_ = com.dslplatform.json.BoolConverter.deserialize(reader);
						reader.getNextToken();
						break;
					default:
						reader.getNextToken();
						reader.skip();
				}
			}
			if (reader.last() != '}') throw reader.newParseError("Expecting '}' for object end");
			return new org.krittaphato3.mcping.model.ChatObject(_text_, _color_, _bold_);
		}
	}
}
