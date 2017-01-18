using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.Collections;

public class Vidas : MonoBehaviour {
    public static int vidas;
    public Text textoVidas;
    public Text gameOver;
    public GameObject bola;
    public AudioSource gamOv;
    // Use this for initialization
    void Start () {
        resetVidas();
        textoVidas.text = "vidas: " + vidas;
        gameOver.text = "";
    }
	
	// Update is called once per frame
	void Update () {
	
	}

    public void pierdeVida()
    {
        vidas--;
        textoVidas.text = "vidas: " + vidas;
        if(vidas < 0)
        {
            gamOv.Play();
            gameOver.text = "game over";
            Destroy(bola);
            Invoke("salir", 3.0f);
        }
    }
    public void resetVidas()
    {
        vidas = 3;
    }

    public void salir()
    {
        SceneManager.LoadScene(0);
    }

}
