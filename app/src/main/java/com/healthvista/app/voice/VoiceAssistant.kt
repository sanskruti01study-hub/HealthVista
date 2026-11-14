package com.healthvista.app.voice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import java.util.Locale
import com.healthvista.app.VoiceCommandHandler
import com.healthvista.app.ElderVoiceMode
class VoiceAssistant(
    private val context: Context,
    private val onCommandRecognized: (String) -> Unit,
    private val onError: (String) -> Unit = {}
) {
    var elderModeEnabled=false
    private var speechRecognizer: SpeechRecognizer? = null
    private var textToSpeech: TextToSpeech? = null

    init {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.getDefault()
            }
        }

        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

            speechRecognizer?.setRecognitionListener(
                object : RecognitionListener {
                    override fun onReadyForSpeech(params: Bundle?) {}
                    override fun onBeginningOfSpeech() {}
                    override fun onRmsChanged(rmsdB: Float) {}
                    override fun onBufferReceived(buffer: ByteArray?) {}
                    override fun onEndOfSpeech() {}
                    override fun onPartialResults(partialResults: Bundle?) {}
                    override fun onEvent(eventType: Int, params: Bundle?) {}

                    override fun onResults(results: Bundle?) {
                        val matches = results?.getStringArrayList(
                            SpeechRecognizer.RESULTS_RECOGNITION
                        )

                        matches?.firstOrNull()?.let {
                            onCommandRecognized(it)
                            if (elderModeEnabled) {
                                speak(ElderVoiceMode.getMessage(it))
                            } else {
                                speak(VoiceCommandHandler.getReply(it))
                            }
                        }
                    }

                    override fun onError(error: Int) {
                        onError("Voice recognition failed. Please try again.")
                    }
                }
            )
        } else {
            onError("Speech recognition is not available.")
        }
    }

    fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )

        speechRecognizer?.startListening(intent)
    }

    fun speak(message: String, elderMode: Boolean = elderModeEnabled) {
        val speed = if (elderMode) 0.75f else 1.0f
        textToSpeech?.setSpeechRate(speed)

        textToSpeech?.speak(
            message,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "health_vista_voice"
        )
    }

    fun stopListening() {
        speechRecognizer?.stopListening()
    }

    fun release() {
        speechRecognizer?.destroy()
        speechRecognizer = null

        textToSpeech?.stop()
        textToSpeech?.shutdown()
        textToSpeech = null
    }
}
