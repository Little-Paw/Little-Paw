package com.upb.littlepaw;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.api.gax.rpc.ApiException
import com.google.cloud.dialogflow.v2beta1.DetectIntentRequest
import com.google.cloud.dialogflow.v2beta1.QueryInput
import com.google.cloud.dialogflow.v2beta1.SessionName
import com.google.cloud.dialogflow.v2beta1.SessionsClient
import com.google.cloud.dialogflow.v2beta1.SessionsSettings
import com.google.protobuf.Struct
import com.google.protobuf.Value
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext


public class DialogflowViewModel(application:Application) : AndroidViewModel(application) {

    private lateinit var sessionsClient: SessionsClient
    private lateinit var sessionName: SessionName

        init {
        // Configurar la conexión con Dialogflow
        val credentials: GoogleCredentials = GoogleCredentials.fromStream(
        application.assets.open("agent.json")
        )
        val projectId = "joamyupbbot-ingh"
        val region = "global" // cambiar por la región del agente

        val settingsBuilder = SessionsSettings.newBuilder()
        .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
        .setEndpoint("$projectId-$region.dialogflow.googleapis.com")
        .build()
        sessionsClient = SessionsClient.create(settingsBuilder)
        sessionName = SessionName.of(projectId, "test-session")

    }
}

