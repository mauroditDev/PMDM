using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class SceneLoader : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    public void empezar()
    {
        Debug.Log("Click!");
        SceneManager.LoadScene(1);
    }

    public void restart()
    {
        SceneManager.LoadScene(0);
    }

}
