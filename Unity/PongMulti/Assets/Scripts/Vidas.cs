using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.Collections;

public class Vidas : MonoBehaviour {
    public static int vidas;
    public static int izq;
    public static int dch;
    public Text textoVidas;
    public Text gameOver;
    public GameObject bola;
    public AudioSource gamOv;
    // Use this for initialization
    void Start () {
        izq = 0;
        dch = 0;
        textoVidas.text = izq+"  -  " +dch;
        gameOver.text = "";
    }
	
	// Update is called once per frame
	void Update () {
	
	}

    public void Izq()
    {
        izq++;
        textoVidas.text = izq + "  -  " + dch;
        if(izq-dch > 4)
        {
            gameOver.text = "Victoria jugador Izquierda!\n" + izq + " puntos";
            Invoke("salir", 6);
        }
    }

    public void Dch()
    {
        dch++;
        textoVidas.text = izq + "  -  " + dch;
        if (dch - izq > 4)
        {
            gameOver.text = "Victoria jugador Derecha!\n" + dch + " puntos";
            Invoke("salir", 6);
        }
    }

    public void salir()
    {
        SceneManager.LoadScene(0);
    }

    public void Reset()
    {
        izq = 0;
        dch = 0;
        textoVidas.text = izq + "  -  " + dch;
        gameOver.text = "";
    }

}
